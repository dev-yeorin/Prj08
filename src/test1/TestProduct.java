package test1;

import java.util.Scanner;

interface IPO {
	void input();
	void process();
	void output();
}

class Prom {
	//입력data : 사번,이름,입사일,월급,부서번호
	//			num,name,join,pay,dnum
	//Field
	private int num;
	private String name;
	private int join;
	private double pay;
	private int dnum;
	
	//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
	//			num,name,join,pay,bonus,tot,dname
	private double bonus;
	private double tot;
	private String dname;

	//constructor
	public Prom(int num, String name, int join, double pay, int dnum) {
		super();
		this.num = num;
		this.name = name;
		this.join = join;
		this.pay = pay;
		this.dnum = dnum;
	}

	//Getter/Setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getJoin() {
		return join;
	}
	public void setJoin(int join) {
		this.join = join;
	}
	
	public double getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	
	public int getDnum() {
		return dnum;
	}
	public void setDnum(int dnum) {
		this.dnum = dnum;
	}
	
	
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}

	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

	//toString
	@Override
	public String toString() {
		return "Prom [num=" + num + ", name=" + name + ", join=" + join + ", pay=" + pay + ", dnum=" + dnum + ", bonus="
				+ bonus + ", tot=" + tot + ", dname=" + dname + "]";
	}

	
	
}
class Promotion implements IPO{
	
	private Prom p;

	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("입력:사번,이름,입사일,월급,부서번호");
		String line = in.nextLine();
		String[] li = line.split(",");
		int num 	= Integer.parseInt(li[0].trim());
		String name = li[1].trim();
		int join 	= Integer.parseInt(li[2].trim());
		double pay 	= Double.parseDouble(li[3].trim());
		int dnum 	= Integer.parseInt(li[4].trim());
		
		p			= new Prom(num, name, join, pay, dnum);
		System.out.println(p);
		
		
	}

	@Override
	public void process() {
		// dnum -> dname
		// 10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		int dnum = p.getDnum();
		String dname = "";
		switch (dnum) {
		case 10: dname = "인사"; break;
		case 20: dname = "자재"; break;
		case 30: dname = "총무"; break;
		case 40: dname = "연구개발"; break;
		case 50: dname = "생산"; break;
		case 60: dname = "서비스"; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + dnum);
		}
		p.setDname(dname);
		
		int joinyear = p.getJoin() / 10000;
		int year = 2026 - joinyear;
		// bonus = join * (pay * 0.005)
		p.setBonus(year * (p.getPay() * 0.005));
		// tot = pay + bonus
		p.setTot(p.getPay() + p.getBonus());
		
	}

	@Override
	public void output() {
		System.out.println(p);
		
		String title = "사번  이름   입사일      월급    보너스  수령액  부서명";
		String fmt 	 = "%d  %s  %d  %.2f  %.2f %.2f  %s";
		System.out.println(title);
		System.out.printf(fmt, p.getNum(), p.getName(), p.getJoin(), p.getPay(), p.getBonus(), p.getTot(), p.getDname());
		
	}
	
	
}
public class TestProduct {

	public static void main(String[] args) {
		Promotion p = new Promotion();
		p.input();
		p.process();
		p.output();

	}

}
