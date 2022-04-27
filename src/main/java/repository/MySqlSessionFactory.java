package repository;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class MySqlSessionFactory {
	public SqlSessionFactory sqlmap;
	
	MySqlSessionFactory(){
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
			sqlmap = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("ok config");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
