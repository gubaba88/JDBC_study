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
import kyu.project.dto.MemDetailDTO;

public class MemDetailInsert implements Action {
	// 로그 출력을 위해 템플릿으로 작성
	private static final Log log = LogFactory.getLog(MemDetailInsert.class);

	@Override
	public void execute(Scanner scanner) throws IOException {
		System.out.println();
		System.out.println("===회원 추가 정보 입력 페이지===");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		MemDetailDAO memDetailDAO = new MemDetailDAO();
		MemDetailDTO memDetailDTO = new MemDetailDTO();
		String memId;
		System.out.print("추가정보를 입력할 회원 아이디를 입력하세요 : ");
		memId = scanner.next();
		memDetailDTO = memDetailDAO.memDetailIdCheck(memId);
		if (memDetailDTO.getMemId() == null) {
			memDetailDTO.setMemId(memId);
			System.out.println("추가 정보 입력을 시작 합니다. 불확실한 정보 입력으로 발생되는 손해는 책임지지 않습니다.");
			System.out.print("이름을 등록하시겠습니까?(Y) : ");
			String choiceName = scanner.next();
			if (choiceName.equals("y") || choiceName.equals("Y")) {
				System.out.print("등록할 이름을 입력해주세요 : ");
				String memName = scanner.next();
				memDetailDTO.setMemName(memName);
			}
			System.out.print("생년월일을 등록하시겠습니까?(Y) : ");
			String choiceBirth = scanner.next();
			if (choiceBirth.equals("y") || choiceBirth.equals("Y")) {
				System.out.print("주민번호 앞자리 6숫자를 입력해주세요 : ");
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
			System.out.print("전화번호를 등록하시겠습니까?(Y) : ");
			String choicePhone = scanner.next();
			if (choicePhone.equals("y") || choicePhone.equals("Y")) {
				System.out.print("등록할 전화번호를 입력해주세요 : ");
				String memPhone = scanner.next();
				memDetailDTO.setMemPhone(memPhone);
			}
			System.out.print("주소를 등록하시겠습니까?(Y) : ");
			String choiceAddress = scanner.next();
			if (choiceAddress.equals("y") || choiceAddress.equals("Y")) {
				System.out.print("등록할 주소를 입력해주세요 : ");
				String memAddress = bufferedReader.readLine();
				memDetailDTO.setMemAddress(memAddress);
			}
			System.out.print("한줄 소개를 등록하시겠습니까?(Y) : ");
			String choiceComment = scanner.next();
			if (choiceComment.equals("y") || choiceComment.equals("Y")) {
				System.out.print("등록할 한줄 소개를 입력해주세요 : ");
				String memComment = bufferedReader.readLine();
				memDetailDTO.setMemComment(memComment);
			}
			log.info("입력할 정보 - " + memDetailDTO);
			memDetailDAO.memDetailInsert(memDetailDTO);
			System.out.println("추가 정보 입력이 완료 되었습니다.");
		} else {
			System.out.println("이미 추가 정보가 등록된 회원 입니다.");
			log.info("추가 정보 입력 확인 - " + memDetailDTO.getMemId().equals(memId));
		}
	}
}
