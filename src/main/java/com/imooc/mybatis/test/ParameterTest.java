package com.imooc.mybatis.test;

import com.imooc.mybatis.bean.Person;
import com.imooc.mybatis.dao.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class ParameterTest {

    public static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            Reader reader = null;
            try {
                reader = Resources.getResourceAsReader(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    @Test
    public void deletePerson() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        mapper.deletePerson(4);

        sqlSession.commit();
    }

    @Test
    public void getPersonByNameAndGender() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        Person person = mapper.getPersonByNameAndGender("wangwu", "f");

        System.out.println(person);
    }

    @Test
    public void collectionTest() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        Person personByCollection = mapper.getPersonByCollection(new int[] {1,2});

        System.out.println(personByCollection);
    }

    @Test
    public void foreachTest() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        List<Person> personList = mapper.getPersonByIds(new int[]{1, 2, 3, 4, 5});

        System.out.println(personList);
    }

    @Test
    public void processMyBatisBatchTest() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Person person = new Person("tom" + i, "email@" + i, "f");
            persons.add(person);
        }

        mapper.addPerson(persons);

        sqlSession.commit();
    }

    @Test
    public void batchForExecutorTest() {
        SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH);

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            mapper.addPersons(new Person("tom" + i, "email@" + i, "f"));
        }

        long endTime = System.currentTimeMillis();

        sqlSession.commit();
        sqlSession.close();

        System.out.println(endTime - beginTime);
    }

}
