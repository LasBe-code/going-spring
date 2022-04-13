package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.Business;
import model.Picture;
import model.Room;
import mybatis.ReservedMapperAnno;
import util.MybatisConnection;

public class SearchDao {
		public List<Business> businessList(Map map) {
			
			SqlSession sqlSession = MybatisConnection.getConnection();
			try {
				List<Business> list = sqlSession.getMapper(ReservedMapperAnno.class).businessList(map);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MybatisConnection.close(sqlSession);
			}
			return null;
		}
public List<Picture> sbPicList(int pic_num) {
			
			SqlSession sqlSession = MybatisConnection.getConnection();
			try {
				List<Picture> list = sqlSession.getMapper(ReservedMapperAnno.class).sbPicList(pic_num);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MybatisConnection.close(sqlSession);
			}
			return null;
		}
public String roomMinPrice(String bu_email) {
	SqlSession sqlSession = MybatisConnection.getConnection();
	try {
		return sqlSession.getMapper(ReservedMapperAnno.class).roomMinPrice(bu_email);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MybatisConnection.close(sqlSession);
	}
	return null;
}

public List<Business> buidList(Map map) {
    SqlSession sqlSession = MybatisConnection.getConnection();
    try {
        List<Business> list = sqlSession.getMapper(ReservedMapperAnno.class).buidList(map);
        return list;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        MybatisConnection.close(sqlSession);
    }
    return null;
}
}