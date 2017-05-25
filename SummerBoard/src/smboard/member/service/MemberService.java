package smboard.member.service;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import smboard.member.dao.MemberDao;
import smboard.member.model.MemberModel;

public class MemberService implements MemberDao {

	private SqlMapClientTemplate sqlMapClientTemplate;
	
	
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@Override
	public boolean addMember(MemberModel memberModel) {
		// TODO Auto-generated method stub
		
		sqlMapClientTemplate.insert("member.addMember",memberModel);
		
		MemberModel checkAddMember = findByUserId(memberModel.getUserId());
		
		//check addMember process
		if(checkAddMember == null){
		
			return false;
		
		}else{
			
			return true;
		}		
	}

	@Override
	public MemberModel findByUserId(String userId) {
		// TODO Auto-generated method stub
		return (MemberModel)sqlMapClientTemplate.queryForObject("member.findByUserId",userId);
	}
	
}
