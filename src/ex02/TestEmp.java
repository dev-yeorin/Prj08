package ex02;

import java.time.LocalDateTime;
import java.util.Scanner;

//입력 1줄 -> 결과 1줄
//입력datprivate String : 사번,이름,입사일,월급,부서번호
//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명

//금액은 소수이하 두자리로 반올림
//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
//수령액   =  월급 + 보너스 
//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스

//모든기능은 class에 구현한다.
//입력data
//사번,이름,입사일,월급,부서번호 
/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
*/

interface    Ipo {
	void   input();
	void   process();
	void   output();
}

// Datprivate String class
class   EmpVo {
	// Field
	//입력data : 사번,이름,입사일,월급,부서번호
	//           num, name, hiredate, salary, deptid	
	private String  num; 
	private String  name; 
	private String  hiredate; 
	private double  salary;
	private int     deptid;
	
	//출력     : 사번 이름  입사일   월급     보너스  수령액  부서명
	//           num, name, hiredate, salary, bonus,  pay,    deptname
	private double  bonus;
	private double  pay;    
	private String  deptname;
	
	//Constructor
	public EmpVo(String num, String name, String hiredate, 
			double salary, int deptid) {
		
		this.num = num;
		this.name = name;
		this.hiredate = hiredate;
		this.salary = salary;
		this.deptid = deptid;
		
	}
	
	// Getter /Setter
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public double getSalary() {
		return salary;
	}
	public double getBonus() {
		return bonus;
	}
	public double getPay() {
		return pay;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	// toString
	@Override
	public String toString() {
		return "EmpVo [num=" + num + ", name=" + name + ", hiredate=" + hiredate + ", salary=" + salary + ", deptid="
				+ deptid + ", bonus=" + bonus + ", pay=" + pay + ", deptname=" + deptname + "]";
	}
	
}

// 업무처리 class
class   Employee  implements Ipo  {
	
	private  EmpVo   empVo;  

	@Override
	public void input() {
		// num, name, hiredate, salary, deptid	
		Scanner   in     =  new Scanner(System.in);
		
		System.out.println("입력:사번,이름,입사일,월급,부서번호");
		String    line       =  in.nextLine();
		String [] li         =  line.trim().split(",");
		String    num        =  li[0].trim();
		String    name       =  li[1].trim();
		String    hiredate   =  li[2].trim();
		double    salary     =  Double.parseDouble( li[3].trim() );
		int       deptid     =  Integer.parseInt(   li[4].trim() );
		
		empVo   = new EmpVo(num, name, hiredate, salary, deptid);
		System.out.println( empVo );
		
	}

	@Override
	public void process() {
		// 300,정연,20091003,250.0,30  
		//금액은 소수이하 두자리로 반올림
		// 근무연수
		int      thisYear  =  LocalDateTime.now().getYear();
		//보너스           =  근무연수에 따라 월급의 0.5% 로 계산한다
		// "20091003"
		int      hireYear  =  Integer.parseInt(empVo.getHiredate().substring(0, 4)); 
		int      years     =  thisYear - hireYear; // 올해연도 - 입사연도
		double	 salary    =  empVo.getSalary();
 		double   bonus     =  years * salary * 0.005; 
 		empVo.setBonus( bonus );
 				
 		//수령액           =  월급 + 보너스
 		double   pay       =  empVo.getSalary() + bonus; 
 		empVo.setPay( pay );
		
		//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
 		int      deptid    = empVo.getDeptid();
 		String   deptname  = "";
 		switch ( deptid ) {
		case 10: deptname  = "인사";      break;  
		case 20: deptname  = "자재";      break;  
		case 30: deptname  = "인사";      break;  
		case 40: deptname  = "연구개발";  break;  
		case 50: deptname  = "생산";      break;  
		case 60: deptname  = "서비스";    break;  		
		} 		
 		empVo.setDeptname( deptname );
 	
 		System.out.println( empVo );
	}

	@Override
	public void output() {
		//출력     : 사번 이름  입사일   월급     보너스  수령액  부서명
		//           num, name, hiredate, salary, bonus,  pay,    deptname
		System.out.println("사번 이름  입사일     월급  보너스  수령액  부서명");
		String   fmt   =   "%s  %s  %s    %.2f  %.2f  %.2f   %s\n";
		System.out.printf(fmt, 
				empVo.getNum(), 
				empVo.getName(), 
				empVo.getHiredate(),
				empVo.getSalary(), 
				empVo.getBonus(),  
				empVo.getPay(),
				empVo.getDeptname() );
		
		
	}
	
} 

public class TestEmp {

	public static void main(String[] args) {

		Employee  emp  = new Employee();
		
		emp.input();
		emp.process();
		emp.output();

	}

}