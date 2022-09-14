package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Member;
import com.example.app.domain.MemberType;
import com.example.app.mapper.MemberMapper;
import com.example.app.mapper.MemberTypeMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MemberTypeMapper typeMapper;

	@Override
	public List<Member> getMemberList() throws Exception {
		return memberMapper.selectAll();
	}

	@Override
	public Member getMemberById(Integer id) throws Exception {
		return memberMapper.selectById(id);
	}

	@Override
	public void addMember(Member member) throws Exception {
		memberMapper.insert(member);
	}

	@Override
	public void editMember(Member member) throws Exception {
		memberMapper.update(member);
	}

	@Override
	public void deleteMember(Integer id) throws Exception {
		memberMapper.delete(id);
	}

	@Override
	public List<MemberType> getMemberTypeList() throws Exception {
		return typeMapper.selectAll();
	}

	@Override
	public List<Member> getMemberListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page-1);
		return memberMapper.selectLimited(offset, numPerPage);
	}

	@Override
	public long getTotalPages(int numPerPage) throws Exception {
		double totalNum = (double) memberMapper.count();
		return (int) Math.ceil(totalNum/numPerPage);
	}

}
