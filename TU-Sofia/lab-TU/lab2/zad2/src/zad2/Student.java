package zad2;

public class Student extends Person{
	String fNum;
	float PIK;
	float TE;
	float PPE;
	
	public Student(String fName, String lName, String bDay, String fNum, float PIK, float TE, float PPE) {
		super(fName, lName, bDay);
		setfNum(fNum);
		setPIK(PIK);
		setTE(TE);
		setPPE(PPE);
	}

	public String getfNum() {
		return this.fNum;
	}

	public void setfNum(String fNum) {
		this.fNum = fNum;
	}

	public float getPIK() {
		return this.PIK;
	}

	public void setPIK(float pIK) {
		this.PIK = pIK;
	}

	public float getTE() {
		return this.TE;
	}

	public void setTE(float tE) {
		this.TE = tE;
	}

	public float getPPE() {
		return this.PPE;
	}

	public void setPPE(float pPE) {
		this.PPE = pPE;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", bDay=" + bDay+", fNum=" + fNum + ", PIK=" + PIK + ", TE=" + TE + ", PPE=" + PPE + "]";
	}

	

}
