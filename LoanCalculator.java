import java.awt.GridLayout;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoanCalculator extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
		intialize();

	}

	
	
	public static JFrame intialize()
	{
		JFrame mainFrame = new JFrame("Loan Calculator");
		
		mainFrame.setSize(280,400);
		mainFrame.setLayout(new GridLayout(3,1));
		JPanel p = new JPanel();
		JButton button = new JButton("Calculate");
		
		JLabel loanWord = new JLabel("Loan Amount: $");
		JTextField loanAmount = new JTextField(10);
		JLabel interest = new JLabel("Interest Rate: %");
		JTextField interestRate = new JTextField(10);
		JLabel monthWord = new JLabel("Number of Months: ");
		JTextField months = new JTextField(10);
		
		p.add(loanWord);
		p.add(loanAmount);
		p.add(interest);
		p.add(interestRate);
		p.add(monthWord);
		p.add(months);
		
		p.add(button);
		mainFrame.add(p);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		button.addActionListener(e -> {
			calculate(loanAmount, interestRate, months);
		});
		
		
		
		mainFrame.setVisible(true);
		return mainFrame;
	}
	
	
	public static void calculate(JTextField x, JTextField y, JTextField z)
	{
		
			
		double loan = Double.parseDouble(x.getText());
		double interest = Double.parseDouble(y.getText());
		interest = interest/100;
		int months = Integer.parseInt(z.getText());
		
		if(months < 1)
		{
			System.out.println("Please use 1 or higher for months.");
			System.exit(0);
		}
			
		
		//calculate the monthly loan payment
		double monthlyPayment =  loan * interest/12 * (Math.pow(1 + interest/12, months)) / (Math.pow(1 + interest/12,months) - 1) ;  //calculate monthly payment
		
		
		
		
		//System.out.printf("Principle Payment: $%.2f \n", monthlyPayment - (loan * interest/12));
		//System.out.printf("Computed Interest Due At First Payment: $%.2f \n", (loan * interest/12));
		
		
		double totalInterest = 0;
		
		
		for(int i=1; i <= months; i++)
		{
			totalInterest+= interestDue(loan, interest);
			
			
			System.out.printf("Month: %d Interest Due: $%.2f Principle: $%.2f New Balance: $%.2f \n", i, interestDue(loan, interest), principle(monthlyPayment, loan, interest)  ,loan-= monthlyPayment -(loan * interest/12) );
			
			if(i % 12 == 0)//will display the year for user to keep track
				System.out.println("-------- Year " + i/12 + " End --------");
		
		}
		
		
		
		System.out.println("\n");
		System.out.printf("Monthly Payment: $%.2f \n", monthlyPayment); //gives user the monthly payment
		System.out.printf("Total of " + months + " payments: $%.2f\n", monthlyPayment * months); //gives user total number of payments to make
		System.out.printf("Total Interest Paid: $%.2f or %.0f%%\n", totalInterest,  (totalInterest / (monthlyPayment * months)) * 100 ); //gives user the total interest which will be paid over the loan.
		
	}
	
	
	
	public static double interestDue(double totalLoan, double interestRate)
	{
		return (totalLoan * interestRate /12);
	}
	
	public static double principle(double monPay, double totalLoan, double interestRate)
	{
		return monPay - (totalLoan * interestRate / 12);
	}
}
