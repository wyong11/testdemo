package com.sy.entity;

public class PaperInfo {

	private int pid;
	private String pname;
	private int ptime;
	private int pscore;
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPtime() {
		return ptime;
	}

	public void setPtime(int ptime) {
		this.ptime = ptime;
	}

	public int getPscore() {
		return pscore;
	}

	public void setPscore(int pscore) {
		this.pscore = pscore;
	}
	
	/*
	 * @Override public String toString() { return "ExamPaperInfo [examPaperId=" +
	 * examPaperId + ", examPaperName=" + examPaperName + ",examPaperTime=" +
	 * examPaperTime + ", examPaperScore=" + examPaperScore + "]"; }
	 */

}
