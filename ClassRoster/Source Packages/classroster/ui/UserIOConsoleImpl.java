package classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
	
	final private Scanner console = new Scanner(System.in);

	@Override
	public void print(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
		
	}

	@Override
	public String readString(String prompt) {
		// TODO Auto-generated method stub
		System.out.println(prompt);
		String response = console.nextLine();
		return response;
	}
	
	@Override
	public double readDouble(String prompt) {
		// TODO Auto-generated method stub
		String response;
		while(true) {
			try {
				response = this.readString(prompt);
				return Double.parseDouble(response);
			}catch (NumberFormatException e) {
				this.print("Input error. Please try again");
			}
		}
		
	}

	@Override
	public double readDouble(String prompt, double min, double max) {
		// TODO Auto-generated method stub
		double result;
		do {
			result = this.readDouble(prompt);
;		}while(result < min || result > max);
		return result;
	}

	@Override
	public float readFloat(String prompt) {
		// TODO Auto-generated method stub
		String response;
		while(true) {
			try {
				response = this.readString(prompt);
				return Float.parseFloat(response);
			}catch (NumberFormatException e) {
				this.print("Input error. Please try again");
			}
		}
	}

	@Override
	public float readFloate(String prompt, float min, float max) {
		// TODO Auto-generated method stub
		float result;
		do {
			result = this.readFloat(prompt);
		}while(result < min || result > max);
		
		return result;
	}

	@Override
	public int readInt(String prompt) {
		// TODO Auto-generated method stub
		String response;
		while(true) {
			try {
				response = this.readString(prompt);
				return Integer.parseInt(response);
			}catch(NumberFormatException e) {
				this.print("Input error. Please try again");
			}
		}
	}

	@Override
	public int readInt(String prompt, int min, int max) {
		// TODO Auto-generated method stub
		int result;
		do {
			result = this.readInt(prompt);
		}while(result < min || result > max);
		return result;
	}

	@Override
	public long readLong(String prompt) {
		// TODO Auto-generated method stub
		String response;
		
		while(true) {
			try {
				response = this.readString(prompt);
				return Long.parseLong(response);
			}catch (NumberFormatException e) {
				this.print("Input error. Please try again");
			}
		}
	}

	@Override
	public long readLong(String prompt, long min, long max) {
		// TODO Auto-generated method stub
		long result;
		
		do {
			result = this.readLong(prompt);
		}while(result < min || result > max);
		return result;
	}

}
