package mytest;

import com.github.pagehelper.PageHelper;
import mytest.entity.Actor;
import mytest.mapper.ActorMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        //1、读取配置文件
        String resource = "mytest/config/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        // 2 创建 SqlSessionFactory 工厂：使用 SqlSessionFactoryBuilder 构建者
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        // 3 使用工厂生产 SqlSession 对象
        SqlSession sqlSession = factory.openSession();

        // 4 使用 SqlSession 创建 Mapper 的代理对象
        ActorMapper mapper = sqlSession.getMapper(ActorMapper.class);
        // 5 使用代理对象执行方 法
        PageHelper.startPage(1,2);
        List<Actor> actorList = mapper.getActorByName("NICK");
        for (Actor actor : actorList) {
            System.out.println(actor);
        }


        // 6 释放资源
        sqlSession.close();
        reader.close();
    }
}
