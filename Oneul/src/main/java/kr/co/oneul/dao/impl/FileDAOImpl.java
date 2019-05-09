package kr.co.oneul.dao.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IFileDAO;

@Repository
public class FileDAOImpl implements IFileDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.fileMapper";
	
}
