package kyu.member.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.MemDetailDAO;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemDetailDTO;
import kyu.project.dto.MemberDTO;

public class MemberUpdate implements Action {
	private static final Log log = LogFactory.getLog(MemberUpdate.class);

	@Override
	public void execute(Scanner scanner) throws IOException {
		System.out.println();
		System.out.println("===회원 수정 페이지===");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		MemberDAO memberDAO = new MemberDAO();
		MemDetailDAO memDetailDAO = new MemDetailDAO();
		MemberDTO memberDTO = new MemberDTO();
		MemDetailDTO memDetailDTO = new MemDetailDTO();
		String memId;
		System.out.print("수정할 회원 아이디를 입력하세요 : ");
		memId = scanner.next();
		memberDTO = memberDAO.memberIdCheck(memId);
		if (memberDTO.getMemId() == null) {
			System.out.println("존재하지 않는 아이디입니다.");
		} else {
			log.info("아이디 확인 - " + memberDTO.getMemId());
			memberDTO = memberDAO.memberSelect(memId);
			log.info("회원 정보 - " + memberDTO);
			System.out.println("비밀번호를 수정하시겠습니까?(Y) : ");
			String choicepass = scanner.next();
			if (choicepass.equals("y") || choicepass.equals("Y")) {
				System.out.print("사용할 비밀번호 : ");
				String memPasswd = scanner.next();
				memberDTO.setMemPasswd(memPasswd);
			}
			System.out.println("현재 이메일 : " + memberDTO.getmemEmail());
			System.out.print("수정하시겠습니까?(Y) : ");
			String choiceEmail = scanner.next();
			if (choiceEmail.equals("y") || choiceEmail.equals("Y")) {
				System.out.print("사용할 이메일 : ");
				String memEmail = scanner.next();
				memberDTO.setmemEmail(memEmail);
			}
			memberDTO = memberDAO.memberUpdate(memberDTO);
			System.out.println("회원 기본정보를 수정하였습니다.");
			log.info("회원 기본 정보 수정 - " + memberDTO);
			memDetailDTO = memDetailDAO.memDetailIdCheck(memId);
			log.info(memDetailDTO);
			if (memDetailDTO.getMemId() == null) {
				System.out.println("추가 정보가 등록되지 않은 회원입니다.");
				System.out.print("등록하시겠습니까?(Y) : ");
				String choic = scanner.next();
				if (choic.equals("y") || choic.equals("Y")) {
					MemDetailInsert memDetailInsert = new MemDetailInsert();
					memDetailInsert.execute(scanner);
				}
				System.out.println("회원 메인 화면으로 돌아갑니다.");
				return;
			}
			System.out.print("추가 정보를 수정 하시겠습니까?(Y) : ");
			String choice = scanner.next();
			if (choice.equals("y") || choice.equals("Y")) {
				log.info("아이디 확인 - " + memId);
				memDetailDTO = memDetailDAO.memDetailSelect(memId);
				System.out.println("추가 정보 수정을 시작 합니다. 불확실한 정보 입력으로 발생되는 손해는 책임지지 않습니다.");
				System.out.print("현재 이름 : " + memDetailDTO.getMemName() + ", 수정하시겠습니까?(Y) : ");
				String choiceName = scanner.next();
				if (choiceName.equals("y") || choiceName.equals("Y")) {
					System.out.print("등록할 이름을 입력해주세요 : ");
					String memName = scanner.next();
					memDetailDTO.setMemName(memName);
				}
				System.out.print("현재 생년월일 : " + memDetailDTO.getMemBirth() + ", 수정하시겠습니까?(Y) : ");
				String choiceBirth = scanner.next();
				if (choiceBirth.equals("y") || choiceBirth.equals("Y")) {
					System.out.print("주민번호 앞자리 6자리 숫자를 입력해주세요 : ");
					String memBirth = scanner.next();
					System.out.print("주민번호 뒷자리 첫번째 숫자를 입력해 주세요 : ");
					try {
						int memGender = scanner.nextInt();
						memDetailDTO.setMemGender(memGender);

						if (memGender == 1 || memGender == 2) {
							memBirth = "19" + memBirth;
						} else if (memGender == 1 || memGender == 2) {
							memBirth = "20" + memBirth;
						}
						memDetailDTO.setMemBirth(memBirth);
					} catch (InputMismatchException e) {
						log.info("주민번호 뒷자리 입력에 문자 사용 - " + e);
					}
				}
				System.out.print("현재 전화번호 : " + memDetailDTO.getMemPhone() + ", 수정하시겠습니까?(Y) : ");
				String choicePhone = scanner.next();
				if (choicePhone.equals("y") || choicePhone.equals("Y")) {
					System.out.print("등록할 전화번호를 입력해주세요 : ");
					String memPhone = scanner.next();
					memDetailDTO.setMemPhone(memPhone);
				}
				System.out.println("현재 주소 : " + memDetailDTO.getMemAddress());
				System.out.println("수정하시겠습니까?(Y) : ");
				String choiceAddress = scanner.next();
				if (choiceAddress.equals("y") || choiceAddress.equals("Y")) {
					System.out.print("등록할 주소를 입력해주세요 : ");
					String memAddress = bufferedReader.readLine();
					memDetailDTO.setMemAddress(memAddress);
				}
				System.out.print("현재 한줄 소개 : " + memDetailDTO.getMemComment());
				System.out.println("수정하시겠습니까?(Y) : ");
				String choiceComment = scanner.next();
				if (choiceComment.equals("y") || choiceComment.equals("Y")) {
					System.out.print("등록할 한줄 소개를 입력해주세요 : ");
					String memComment = bufferedReader.readLine();
					memDetailDTO.setMemComment(memComment);
				}
				log.info("입력할 정보 - " + memDetailDTO);
				memDetailDAO.memDetailUpdate(memDetailDTO);
				System.out.println("추가 정보 입력이 완료 되었습니다.");

			}
			System.out.println("회원 정보 수정을 완료하고 돌아갑니다.");
		}
	}
}
