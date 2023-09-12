/*
Shaheer
Searching and Sorting
Enter the file you would like to go through, you can sort the info by salaries, alphabetically, or search by the first name. The program will also always include the tax for every CEO. Outputting the info into a new text file is also an option
*/
import java.io.*;
import javax.swing.*;
import java.text.*;
import java.awt.event.*;
public class Main{
  public static void main(String[] args)throws IOException{
    try{ //try catch for main
      JFrame frame = new JFrame("CEO Fact Finder"); //create JFrame

      frame.setSize(550,450); //size of frame
      frame.setLayout(null); //set layout

      //create components to put on frame
      JLabel label = new JLabel("CEO Canadian Facts");
      JTextArea txtIn = new JTextArea();
      JTextArea txtOut = new JTextArea();
      JButton search = new JButton("Search forenam");
      JButton sort = new JButton("Sorted");
      JButton unsort = new JButton("Show Facts");
      JButton clear = new JButton("Clear");
      JButton alpha = new JButton("Alphabetical Order");

      //set position and size of components, (x position, y position, wide, tall)
      label.setBounds(455,45,100,25);
      txtIn.setBounds(450,38,56,10);
      txtOut.setBounds(3,5,430,250);
      search.setBounds(450,5,150,25);
      sort.setBounds(450,190,150,25);
      unsort.setBounds(450,100,150,25);
      clear.setBounds(450,130,150,25);
      alpha.setBounds(450,160,150,25);

      //glue components on frame
      frame.add(label);
      frame.add(txtIn);
      frame.add(txtOut);
      frame.add(search);
      frame.add(sort);
      frame.add(unsort);
      frame.add(clear);
      frame.add(alpha);

      //display frame
      frame.show();

      String filei = JOptionPane.showInputDialog("Enter input file without .txt"); //asks and stores input file name
      String fileo = JOptionPane.showInputDialog("Enter output file without .txt"); //asks and stores output file name

      //reads and stores to in, in1, and in2
      BufferedReader in = new BufferedReader(new FileReader (filei + ".txt")); 
      BufferedReader in1 = new BufferedReader(new FileReader (filei + ".txt")); 
      BufferedReader in2 = new BufferedReader(new FileReader (filei + ".txt"));

      int line = 0; //line total holder
      int group = 0; //group number holder
      line = countLines(group, filei)/4;

      //arrays for in, in1, and in2
      String nam[] = new String[line];
      String lastnam[] = new String[line];
      String company[] = new String[line];
      double salary[] = new double[line];
      double tax[] = new double[line];

      //in1
      String nam1[] = new String[line];
      String lastnam1[] = new String[line];
      String company1[] = new String[line];
      double salary1[] = new double[line];
      double tax1[] = new double[line];

      //in2
      String nam2[] = new String[line];
      String lastnam2[] = new String[line];
      String company2[] = new String[line];
      double salary2[] = new double[line];
      double tax2[] = new double[line];

      //uses file values from in to put in array, also tax value for sorted
      for(int i=0; i<nam.length; i++){
        nam[i] = in.readLine();
        lastnam[i] = in.readLine();
        company[i] = in.readLine();
        salary[i] = Double.parseDouble(in.readLine());

        if(salary[i] <= 10000000.00){
          tax[i] = 40.00;
        }

        else{
          tax[i] = 53.00;
        }
      } 

      //uses file values from in1 to put in array, also tax value for unsorted
      for(int i=0; i<nam1.length; i++){
        nam1[i] = in1.readLine();
        lastnam1[i] = in1.readLine();
        company1[i] = in1.readLine();
        salary1[i] = Double.parseDouble(in1.readLine());

        if(salary1[i] <= 10000000.00){
          tax1[i] = 40.00;
        }

        else{
          tax1[i] = 53.00;
        }
      }

      //uses file values from in2 to put in array, also tax value for alpha order
      for(int i=0; i<nam2.length; i++){
        nam2[i] = in2.readLine();
        lastnam2[i] = in2.readLine();
        company2[i] = in2.readLine();
        salary2[i] = Double.parseDouble(in2.readLine());

        if(salary2[i] <= 10000000.00){
          tax2[i] = 40.00;
        }

        else{
          tax2[i] = 53.00;
        }
      }

      //closes file readers
      in.close();
      in1.close();
      in2.close();

      NumberFormat cf = NumberFormat.getCurrencyInstance(); //cash format

      //add action listener for search button
      search.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String messi = txtIn.getText();
          int goat = -1;
          goat = searchMethod(messi, nam);
          if(goat > -1){ //if first name is found then it displays the info
            JOptionPane.showMessageDialog(null,nam[goat] + " " + lastnam[goat] + ", " + company[goat] + ", " + cf.format(salary[goat]) + ", " + cf.format(taxMethod(salary[goat],tax[goat])));
          }

          else{ //if name is not found
            JOptionPane.showMessageDialog(null,"The name you have entered is not in our database");
          }
        }
      });


      //add action listener for sort button
      sort.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          sortMethod(nam,lastnam,company,salary,tax); //calls sort method
          String empty = "";
          for(int j=0; j<nam.length; j++){ //loops until every line has been accounted for
            empty+=nam[j] + " " + lastnam[j] + ", " + company[j] + ", " + cf.format(salary[j]) + ", " + cf.format(taxMethod(salary[j],tax[j])) + "\n"; //holds and arranges each name in sort form
          }
          txtOut.setText(empty); //displays string empty2
        }
      });

      //add action listener for unsorted button
      unsort.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String empty1 = "";
          for(int j=0; j<nam1.length; j++){ //loops until every line has been accounted for
            empty1+=nam1[j] + " " + lastnam1[j] + ", " + company1[j] + ", " + cf.format(salary1[j]) + ", " + cf.format(taxMethod(salary1[j],tax1[j])) + "\n"; //holds and arranges each name in unsorted form
          }
          txtOut.setText(empty1); //displays string empty1
        }
      });

      //add action listener for alpha button
      alpha.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          alphabetMethod(nam2,lastnam2,company2,salary2,tax2); //calls alpha method
          String empty2 = "";
          for(int j=0; j<nam2.length; j++){ //loops until every line has been accounted for
            empty2+=nam2[j] + " " + lastnam2[j] + ", " + company2[j] + ", " + cf.format(salary2[j]) + ", " + cf.format(taxMethod(salary2[j],tax2[j])) + "\n"; //holds and arranges each name in alpha form
          }
          txtOut.setText(empty2); //displays string empty2
        }
      });

      //add action listener for clear button
      clear.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          txtOut.setText(""); //when clicked it clears the txtOut 
        }
      });

      //creates output file
      FileWriter taxInfo = new FileWriter(fileo + ".txt");
      PrintWriter output = new PrintWriter(taxInfo);

      //writes display into taxInfo file
      for(int k=0; k<salary.length; k++){
        output.println(nam[k]);
        output.println(lastnam[k]);
        output.println(company[k]);
        output.println(salary[k]);
        output.println(taxMethod(salary[k],tax[k]));
      }

      //closes file writers
      output.close();
    }

    catch(Exception e){
      JOptionPane.showMessageDialog(null,"There seems to be an error, please double check spelling and file names.");
    }
  }

  public static Integer countLines(int a, String filei){ //method which determines number of lines
    int tline = 0; //hold value of total lines

    try(BufferedReader in = new BufferedReader(new FileReader(filei + ".txt"))){
      while(in.readLine() != null) tline++; //continues reading lines until there are no more, and after it reads one line the value of tline is increased by 1
    }

    catch(Exception e){ //displays error message if counter unexpectedly runs into an issue
      JOptionPane.showMessageDialog(null,"There seems to have been an error with the counter, please try again.");
    }

    return tline; //returns variable which will hold # of lines
  }

  public static void sortMethod (String nam[], String lastnam[], String company[], double salary[], double tax[]){ //method to sort CEO's by salary amount
    try{ //try catch for sort method
      for(int i=0; i<nam.length; i++){ //loop that will run until it completes list of names
        for(int j=0; j<nam.length-1; j++){ //loop will compare array values with each other
          if(salary[j] > salary[j+1]){ //compares one array value with another
          //swaps the order of the each individual CEO info to put in increasing order

          //swaps first nam info
          String tempNam = nam[j+1];
          nam[j+1] = nam[j];
          nam[j] = tempNam;

          //swaps last name info
          String tempLastnam = lastnam[j+1];
          lastnam[j+1] = lastnam[j];
          lastnam[j] = tempLastnam;

          //swaps company info
          String tempCompany = company[j+1];
          company[j+1] = company[j];
          company[j] = tempCompany;

          //swaps salary info
          double tempSalary = salary[j+1];
          salary[j+1] = salary[j];
          salary[j] = tempSalary;

          //swaps tax info
          double tempTax = tax[j+1];
          tax[j+1] = tax[j];
          tax[j] = tempTax;
          }
        }
      }
    }

    catch(Exception e){ //displays error message if error is detected
      JOptionPane.showMessageDialog(null,"Error detected in sort method.");
    }
  }

  public static double taxMethod(double b, double c){ //method for tax calculation
    double taxcalc = b*c/100; //gets value of tax

    return taxcalc;
  }

  public static int searchMethod(String messi, String nam[]){
    int look = -1;
    try{ //try catch for search method
      
      for(int i=0; i<nam.length; i++){ //loop goes through every first name
        if(messi.equalsIgnoreCase(nam[i])){ //if first name is found, then stops the loop
          look = i;
          break;
        }
      }
    }

    catch(Exception e){ //displays error message if error is detected
      JOptionPane.showMessageDialog(null,"Error detected in search method.");
    }
    return look;
  }

  public static void alphabetMethod(String nam[], String lastnam[], String company[], double salary[], double tax[]){ //method to sort CEO's alphabetically
    try{
      String words1,words2;
      int len1,len2,sm;
      int ascii1[], ascii2[];

      for(int i=0; i<nam.length; i++){ //loops until every first name as been accounted for
        for(int j=0; j<nam.length-1; j++){ //loop compares first  names with each other
          words1 = nam[j];
          words2 = nam[j+1];

          len1 = words1.length(); //converts to # of letters
          len2 = words2.length(); //converts to # of letters

          ascii1 = new int[len1]; //decides amount each integer array will hold
          ascii2 = new int[len2]; //decides amount each integer array will hold

          for(int k=0; k<len1; k++){
            ascii1[k] = (int)words1.charAt(k); //converts int to ascii value
          }

          for(int l=0; l<len2; l++){
            ascii2[l] =(int)words2.charAt(l); //converts int to ascii value
          }

          sm = len1 <= len2 ? len1 : len2; //ternary operator, if true then left side operates, if not the right side does, finds smaller word and runs loop on that basis
        
          int flag = -1; //value for flag

          for(int c=0; c<sm; c++){ 
            if(ascii1[c] < ascii2[c]){ //if first word letter is less than second then a new value is set and breaks 
              flag = 1;
              break;
            }

            else if(ascii1[c] > ascii2[c]){ //if second word letter is less than first then a new value is set for flag and breaks
              flag = 2;
              break;
            }
          }

          if(flag==-1){ //in case no flag is approached

          }

          else if(flag==1){ //if the first word comes first then everything will remain the same

          }

          else if(flag==2){ //if second word comes first then it swaps info
            String tempFirst1 = nam[j];
            nam[j] = nam[j+1];
            nam[j+1] = tempFirst1;

            String tempLast1 = lastnam[j];
            lastnam[j] = lastnam[j+1];
            lastnam[j+1] = tempLast1;

            String tempCompany1 = company[j];
            company[j] = company[j+1];
            company[j+1] = tempCompany1;

            double tempSalary1 = salary[j];
            salary[j] = salary[j+1];
            salary[j+1] = tempSalary1;

            double tempTax1 = tax[j];
            tax[j] = tax[j+1];
            tax[j+1] = tempTax1;
          }
        }
      }
    }
    catch(Exception e){ //try catch for alpha
      JOptionPane.showMessageDialog(null,"We cannot sort alphabetically");
    }    
  }
}
