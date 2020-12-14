package algorithm;

import java.util.Scanner;

public class SaveGrade {

	private static int subjectNum;
	private static int preference;
	private static int[] basicInfo = null;

	private static int[][] exam = null; // 첫번째 col 값은 시험 날짜, 두번째 col 값은 그 날의 몇 번째인지.
	private static String[] subjectTitle = null;
	private static int[] subjectType = null; // 전공인지 교양인지 체크
	private static int[] subjectCredit = null;
	private static int[][] feature = null; // 첫번째 col 값은 난이도, 두번째 col 값은 ppt 양의 정도
	private static float[] studyTime = null;
	private static float[] weight = null;
	private static int[] greedyOutput = null; // Greedy를 통해 구한 output을 저장할 배열임.

	
	public SaveGrade(){
		int i;

		getInput(); // input 입력

		for (i = 0; i < subjectNum; i++) // 과목별 필요한 공부시간 계산
		{
			studyTime[i] = getStudyTime(i);
		}
		for (i = 0; i < subjectNum; i++) // 과목별 weight를 계산
		{
			weight[i] = getWeight(i);
		}

		Greedy();

		testvalue();
		
		//여기에 새로운 GUI(전종민씨가 만든거) 넣으면 됩니다.
		printOutput();
	}

	
	//getter! 사용법 : package algorithm 내의 이 값이 필요한 클래스 내에서 SaveGrade.(getter명) 으로 호출해서 거기서 값을 받으면 됩니다
	//ex) int[] basicinfo = SaveGrade.getBasicInfo();
	//순위와 시간 사용법은 아래 printOutput보면서 습득하시길 바랍니다. 
	public static int[] getGreedyOutput() {
		return greedyOutput;
	}
	
	public static float[] getStudyTime() {
		return studyTime;
	}
	
	public static String[] getSubjectTitle() {
		return subjectTitle;
	}
	
	public static int[] getBasicInfo() {
		//남은 일수 (일), 하루 공부량 - 주중 (시간), 하루 공부량 - 주말(시간), 전공/교양 선호도(1/0)
		return basicInfo;
	}
	
	
	public static void printOutput() {
		int i;

		System.out.println("--------------------------------------------------------------------------");
		for (i = 0; i < subjectNum; i++) {
			System.out.println(
					i + 1 + "순위 : " + subjectTitle[greedyOutput[i]] + " : " + studyTime[greedyOutput[i]] + " 시간");
		}
	}
	
	
	

	private static void Greedy() {
		int i, j;
		int count = 0;
		float max, pre_max = 1000;
		int max_idx;

		float[] weight_copy = weight.clone();

		if (preference == 1) // 선호도가 전공일 때
		{
			pre_max = 1000;
			for (i = 0; i < subjectNum; i++) // 전공 공부 순서 잡는 과정
			{
				max = 0;
				max_idx = -1;
				for (j = 0; j < subjectNum; j++) {
					if (subjectType[j] == 1) {
						if (weight_copy[j] <= pre_max && max < weight_copy[j]) {
							max = weight_copy[j];
							max_idx = j;
						}
					}
				}
				if (max_idx != -1) {
					pre_max = max;
					greedyOutput[count] = max_idx;
					count++;
					weight_copy[max_idx] = 0;
				}
			}

			pre_max = 1000;
			for (i = 0; i < subjectNum; i++) // 교양 공부 순서 잡는 과정
			{
				max = 0;
				max_idx = -1;
				for (j = 0; j < subjectNum; j++) {
					if (subjectType[j] == 2) {
						if (weight_copy[j] <= pre_max && max < weight_copy[j]) {
							max = weight_copy[j];
							max_idx = j;
						}
					}
				}
				if (max_idx != -1) {
					pre_max = max;
					greedyOutput[count] = max_idx;
					count++;
					weight_copy[max_idx] = 0;
				}
			}

		} else // 선호도가 교양일 때
		{
			pre_max = 1000;
			for (i = 0; i < subjectNum; i++) // 교양 공부 순서 잡는 과정
			{
				max = 0;
				max_idx = -1;
				for (j = 0; j < subjectNum; j++) {
					if (subjectType[j] == 2) {
						if (weight_copy[j] <= pre_max && max < weight_copy[j]) {
							max = weight_copy[j];
							max_idx = j;
						}
					}
				}
				if (max_idx != -1) {
					pre_max = max;
					greedyOutput[count] = max_idx;
					count++;
					weight_copy[max_idx] = 0;
				}
			}

			pre_max = 1000;
			for (i = 0; i < subjectNum; i++) // 전공 공부 순서 잡는 과정
			{
				max = 0;
				max_idx = -1;
				for (j = 0; j < subjectNum; j++) {
					if (subjectType[j] == 1) {
						if (weight_copy[j] <= pre_max && max < weight_copy[j]) {
							max = weight_copy[j];
							max_idx = j;
						}
					}
				}
				if (max_idx != -1) {
					pre_max = max;
					greedyOutput[count] = max_idx;
					count++;
					weight_copy[max_idx] = 0;
				}
			}

		}

		for (i = 0; i < subjectNum - 1; i++) // 같은 weight가 있는지 확인하는 과정
		{
			// 같은 weight가 있는지 확인해서 있다면 더 빠른 일정을 앞으로 swap해야 함
			if (weight[greedyOutput[i]] == weight[greedyOutput[i + 1]]) {
				if (subjectType[greedyOutput[i]] == subjectType[greedyOutput[i + 1]]) {
					swap(i, i + 1);
				}
			}
		}

	}

	private static void swap(int first, int second)  //같은 weight를 가진 과목을 시험 일정에 따라서 swap 해주는 함수
	{
		int date1, date2;
		int order1, order2;
				
		int temp;
		
		//시험 날짜
		date1 = exam[greedyOutput[first]][0];
		date2 = exam[greedyOutput[second]][0];
		
		//그 날짜의 몇번째 순서인지
		order1 = exam[greedyOutput[first]][1];
		order2 = exam[greedyOutput[second]][1];
		
		
		if(date1 < date2)
		{
			//second가 앞으로 와야 함.
			temp = greedyOutput[first];
			greedyOutput[first] = greedyOutput[second];
			greedyOutput[second] = temp;
		}
		else if(date1 > date2)
		{
			//변화 없음.
			return;
		}
		else
		{
			if(order1 > order2)
			{
				//second가 앞으로 와야 함.
				temp = greedyOutput[first];
				greedyOutput[first] = greedyOutput[second];
				greedyOutput[second] = temp;
			}
			else
			{
				//변화 없음.
				return;
			}
		}
		
	}

	private static float getWeight(int index) // 과목별 weight를 계산해주는 함수
	{
		float result;

		result = studyTime[index] * subjectCredit[index];

		return result;
	}

	private static float getStudyTime(int index) // 과목별 필요한 공부시간을 계산해주는 함수
	{
		float difficulty;
		float result;

		difficulty = (float) feature[index][0] / 10;
		result = difficulty * feature[index][1] * feature[index][1];

		return result;
	}
	
	private static void getInput()  //input을 입력 받는 함수
	{
		
		datasaver[] subjectInfo = new datasaver[10];	
		
		subjectInfo = infoSubmit.subjectInfo;
		basicInfo = infoSubmit.basicInfo;
		
		subjectNum = infoSubmit.len;
		
		if(basicInfo[3]==0) preference = 1;
		else preference = 2;
			
		
		//과목 수만큼 배열 공간 할당해주는 과정
		exam = new int[subjectNum][2];
		subjectTitle = new String[subjectNum];
		subjectType = new int[subjectNum];
		subjectCredit = new int[subjectNum];
		feature = new int[subjectNum][2];
		studyTime = new float[subjectNum];
		weight = new float[subjectNum];
		greedyOutput = new int[subjectNum];
		
		
		for(int i=0 ; i<subjectNum ; i++) {
			subjectTitle[i] = subjectInfo[i].getName();
			
			if(subjectInfo[i].getMajor()) subjectType[i] = 1;
			else subjectType[i] = 2;
			
			subjectCredit[i] = subjectInfo[i].getCredit();
			
			
			feature[i][0] = subjectInfo[i].getDiff();
			feature[i][1] = subjectInfo[i].getPPT();
			
			exam[i][0] = subjectInfo[i].getDay();
					
			//그 과목이 입력한 날짜에서 몇 번째로 시험을 보는지 입력하세요 : ")
			exam[i][1] = subjectInfo[i].getTime();

		}

	}
	
	
	private static void testvalue() {
		System.out.println();

		System.out.println("subjectNum : " + subjectNum);
		System.out.println("preference : " + preference);

		for(int i=0;i<subjectNum;i++) {
			System.out.println("exam1 : " + exam[i][0]);
			System.out.println("exam2 : " + exam[i][1]);
			
			System.out.println("subjectTitle : " + subjectTitle[i]);
			System.out.println("subjectType : " + subjectType[i]);
			System.out.println("subjectCredit : " + subjectCredit[i]);
			
			System.out.println("feature1 : " + feature[i][0]);
			System.out.println("feature2 : " + feature[i][1]);

			
			System.out.println("studyTime : " + studyTime[i]);
			System.out.println("weight : " + weight[i]);
			System.out.println("greedyOutput : " + greedyOutput[i]);

			
			
		}

	}
}

