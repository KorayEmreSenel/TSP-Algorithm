//Koray Emre Senel - 150117037
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KorayEmreSenelHW3 {
	
	public static class Cities{

		double ID;
		double X ;
		double Y ;
		double R ;
		int quadrant;
		public Cities(double id, double x, double y) {
			ID = id;
			X = x;
			Y = y;
			quadrant = 0;
		}
		public double getX() {
			return X;
		}
		public double getY() {
			return Y;
		}
		public double getR() {
			return R;
		}
		public void setX(double x) {
			X = X-x;
		}
		public void setY(double y) {
			Y = Y-y;
		}
		public void setR() {
			R = Math.sqrt(Math.pow(Y, 2)+Math.pow(X, 2));
			
			if(X >= 0)
				if (Y >= 0)
					quadrant = 0;
				else quadrant = 3;
			else 
				if (Y >= 0)
					quadrant = 1;
				else quadrant = 2;
		}

		
	}
	
	public static ArrayList<Cities> inputArrStart = new ArrayList<Cities>();
	public static String txtOutputName = "test-output-#.txt";
	public static void main(String[] args) throws IOException {
	
		File file = new File("test-input-#.txt"); 
		ArrayList<Cities> inputArr = new ArrayList<Cities>();
		Scanner sc = new Scanner(file); 
		double xtotal = 0 ;
		double ytotal = 0 ;
		while (sc.hasNextLine()) {
			String scNext = sc.nextLine();
			String cityString [] = new String [3];
			cityString = scNext.split("\\s+",3);
			Cities objt = new Cities(Double.parseDouble(cityString[0]),Double.parseDouble(cityString[1]),Double.parseDouble(cityString[2]));
			xtotal += objt.X;
			ytotal += objt.Y;
			inputArr.add(objt);
			Cities objtStart = new Cities(Double.parseDouble(cityString[0]),Double.parseDouble(cityString[1]),Double.parseDouble(cityString[2]));
			inputArrStart.add(objtStart);
		}
		xtotal = xtotal/inputArr.size();
		ytotal = ytotal/inputArr.size();
		mover (inputArr,xtotal,ytotal);
		sc.close();
		int p = (int) Math.sqrt(inputArr.size());
		int split =(int) Math.ceil(Math.pow(Math.log10(inputArr.size()), Math.log10(inputArr.size()))/p);
		arrSplit(inputArr,split);
		
	}
	
	 public static void arrSplit (ArrayList<Cities> inputArr ,int split) throws IOException {
		 	ArrayList<Cities> arr1 = new ArrayList<Cities>();
		 	ArrayList<Cities> arr2 = new ArrayList<Cities>();
		 	ArrayList<Cities> arr3 = new ArrayList<Cities>();
		 	ArrayList<Cities> arr4 = new ArrayList<Cities>();
		 	ArrayList<ArrayList> cityArr0 = new ArrayList <ArrayList>();
		 	
		 	ArrayList<Cities> arrB1 = new ArrayList<Cities>();
		 	ArrayList<Cities> arrB2 = new ArrayList<Cities>();
		 	ArrayList<Cities> arrB3 = new ArrayList<Cities>();
		 	ArrayList<Cities> arrB4 = new ArrayList<Cities>();
		 	ArrayList<ArrayList> cityArr1 = new ArrayList <ArrayList>();
		 	
		 	cityArr0.add(arr1);
		 	cityArr0.add(arr2);
		 	cityArr0.add(arr3);
		 	cityArr0.add(arr4);
		 	
		 	cityArr1.add(arrB1);
		 	cityArr1.add(arrB2);
		 	cityArr1.add(arrB3);
		 	cityArr1.add(arrB4);
		 	
		 	int size = inputArr.size();
		 	ArrayList<ArrayList> pathArr = new ArrayList<ArrayList>();  
		 	double range = (inputArr.get(size-1).R - inputArr.get(0).R)/split;
		 	
		 	for (int j = 0 ; j<4  ; j++) {
			 	double current = inputArr.get(0).R;
			 	double currentStart = current;
			 	double current2 = inputArr.get(size-1).R;
			 	int i = 0;
			 	Cities lastCity = null;
			 	Cities lastCityBack = null;
			 	int k = 1;
			 	boolean close1 = true;
			 	boolean close2 = true;
		 		boolean firstTime = true;
			 	ArrayList <Cities> path = new ArrayList<Cities>();
			 	ArrayList <Cities> pathBack = new ArrayList<Cities>();
			 	
		 		while(current < inputArr.get(size-1).R + range ) {
		 			double xytotals [][] = new double[2][4];
		 			while(inputArr.get(i).R < current + range && close1) {
		 				switch(j) {
		 				case (0):
		 					if (inputArr.get(i).X >= 0) {
		 						Cities objt = new Cities(inputArr.get(i).ID,inputArr.get(i).X,inputArr.get(i).Y);
		 						cityArr0.get(inputArr.get(i).quadrant).add(objt);
		 						xytotals[0][inputArr.get(i).quadrant] += inputArr.get(i).X;
		 						xytotals[1][inputArr.get(i).quadrant] += inputArr.get(i).Y;
		 					}
		 					break ;
		 				case (1):
		 					if (inputArr.get(i).X < 0) {
		 						Cities objt = new Cities(inputArr.get(i).ID,inputArr.get(i).X,inputArr.get(i).Y);
		 						cityArr0.get(inputArr.get(i).quadrant).add(objt);
		 						xytotals[0][inputArr.get(i).quadrant] += inputArr.get(i).X;
		 						xytotals[1][inputArr.get(i).quadrant] += inputArr.get(i).Y;
		 					}
		 					break;
		 				case (2):
		 					if (inputArr.get(i).Y >= 0) {
		 						Cities objt = new Cities(inputArr.get(i).ID,inputArr.get(i).X,inputArr.get(i).Y);
		 						cityArr0.get(inputArr.get(i).quadrant).add(objt);
		 						xytotals[0][inputArr.get(i).quadrant] += inputArr.get(i).X;
		 						xytotals[1][inputArr.get(i).quadrant] += inputArr.get(i).Y;
		 					}
		 					break;
		 				case (3):
		 					if (inputArr.get(i).Y < 0) {
		 						Cities objt = new Cities(inputArr.get(i).ID,inputArr.get(i).X,inputArr.get(i).Y);
		 						cityArr0.get(inputArr.get(i).quadrant).add(objt);
		 						xytotals[0][inputArr.get(i).quadrant] += inputArr.get(i).X;
		 						xytotals[1][inputArr.get(i).quadrant] += inputArr.get(i).Y;
		 					}
		 					break;
		 				}
		 				if(i < size-1)
		 				i++;
		 				else 
		 					close1 = false;
		 			}
		 			
		 			for (int a = 0 ; a<4 ; a++) {
		 			if(!cityArr0.get(a).isEmpty()) {
		 			mover(cityArr0.get(a),xytotals[0][a],xytotals[1][a]);
		 			lastCity = pathConstruct(cityArr0.get(a),lastCity,path);
		 			}
		 		}
		 			current = current + range;
		 		}
		 		
		 		while(currentStart < inputArr.get(size-1).R + range ) {
		 			double xytotals [][] = new double[2][4];
		 		while(inputArr.get(size-k).R > current2-range && close2 ) {
	 				switch(j) {
	 				case (0):
		 				if (inputArr.get(size-k).X < 0) {
		 					Cities objt = new Cities(inputArr.get(size-k).ID,inputArr.get(size-k).X,inputArr.get(size-k).Y);
	 						cityArr1.get(inputArr.get(size-k).quadrant).add(objt);
		 					xytotals[0][inputArr.get(size-k).quadrant] += inputArr.get(size-k).X;
	 						xytotals[1][inputArr.get(size-k).quadrant] += inputArr.get(size-k).Y;
		 				}
	 					break;
	 				case (1):	
		 					if (inputArr.get(size-k).X >= 0) {
		 						Cities objt = new Cities(inputArr.get(size-k).ID,inputArr.get(size-k).X,inputArr.get(size-k).Y);
		 					cityArr1.get(inputArr.get(size-k).quadrant).add(objt);
		 					xytotals[0][inputArr.get(size-k).quadrant] += inputArr.get(size-k).X;
	 						xytotals[1][inputArr.get(size-k).quadrant] += inputArr.get(size-k).Y;
		 					}
	 					break ;
	 				case (2):
	 					if (inputArr.get(size-k).Y < 0) {
	 						Cities objt = new Cities(inputArr.get(size-k).ID,inputArr.get(size-k).X,inputArr.get(size-k).Y);
	 						cityArr1.get(inputArr.get(size-k).quadrant).add(objt);
	 						xytotals[0][inputArr.get(size-k).quadrant] += inputArr.get(size-k).X;
	 						xytotals[1][inputArr.get(size-k).quadrant] += inputArr.get(size-k).Y;
	 					}
	 					break;
	 				case (3):
	 					if (inputArr.get(size-k).Y >= 0) {
	 						Cities objt = new Cities(inputArr.get(size-k).ID,inputArr.get(size-k).X,inputArr.get(size-k).Y);
	 						cityArr1.get(inputArr.get(size-k).quadrant).add(objt);
	 						xytotals[0][inputArr.get(size-k).quadrant] += inputArr.get(size-k).X;
	 						xytotals[1][inputArr.get(size-k).quadrant] += inputArr.get(size-k).Y;
	 					}
	 					break;
	 				}
	 				
	 				if(k <= size-1)
	 				k++;
	 				else 
	 					close2 = false;
	 			
				}
		 			for(int a = 0 ; a<4 ;a++)
		 			if(!cityArr1.get(a).isEmpty()) {
		 				mover(cityArr1.get(a),xytotals[0][a],xytotals[1][a]);
		 				if(firstTime) {
		 					lastCityBack = pathConstruct(cityArr1.get(a),lastCity,pathBack);
		 					firstTime = false;
		 					continue;
		 				}
		 				lastCityBack = pathConstruct(cityArr1.get(a),lastCityBack,pathBack);
		 				}
		 			current2= current2 - range;
		 			currentStart = currentStart + range;
		 		}
		 		
		 		ArrayList <Cities> pathFinal = new ArrayList<Cities>();
		 		pathCombine(pathFinal,path,pathBack);
		 		pathArr.add(pathFinal);
		 	}
		 	inputArr.clear(); cityArr0.clear(); 
		 	int output[] = pathLengthcheck(pathArr);
		 	outputPrint (pathArr.get(output[0]),output[1]);
	 }
	 
	 private static void pathCombine(ArrayList<Cities> pathFinal, ArrayList<Cities> path, ArrayList<Cities> pathBack) {
		 for (Cities num : path) {
				pathFinal.add(num);
			}
		 for (Cities num : pathBack) {
				pathFinal.add(num);
			}
		
	}

	private static void outputPrint(ArrayList<Cities> arr, int length) throws IOException {
		 FileWriter writer = new FileWriter(txtOutputName); 
		 writer.write((length + System.lineSeparator()));
		for(int i = 0 ; i<arr.size() ; i++)
			writer.write(((int) arr.get(i).ID) + System.lineSeparator());
		
		  writer.close();
	}

	private static int[] pathLengthcheck(ArrayList<ArrayList> pathArr) {
		int length = 0 ;
		int min = 999999999;
		int returns[] = new int [2];
		int best = 0;
		int pathLength[] = new int[4]; 
		for (int i = 0 ; i<4 ; i++) {
			int size = pathArr.get(i).size();
			length = 0 ;
			for(int j = 0 ;j<size-1;j++) {
				int dist = distance((Cities) pathArr.get(i).get(j),(Cities) pathArr.get(i).get(j+1));
				length = length + dist;
			}
			int getBack = distance((Cities)pathArr.get(i).get(size-1),(Cities)pathArr.get(i).get(0));
			length = length + getBack;
			pathLength[i] = length;
		}
		for (int i = 0 ; i<4 ; i++) {
			if(pathLength[i] < min) {
			min = pathLength[i];
			best = i;
		}
		}
		returns[0] = best;
		returns[1] = pathLength[best];
		return returns;
		
	}

	private static Cities pathConstruct(ArrayList<Cities> arr,Cities lastCity,ArrayList<Cities> path) {
		 	int index = 0; 
		 	int min = 999999999 ;
		 	if(lastCity != null)
		 	for (Cities city : arr) {
		 		int id1 = (int) lastCity.ID;
		 		int id2 = (int) city.ID;
		 		if(distance(inputArrStart.get(id1),inputArrStart.get(id2))<min) {
		 			min = distance(inputArrStart.get(id1),inputArrStart.get(id2));
		 			index = arr.indexOf(city);
		 			}
		 		}
		 	if(arr.size()>0)
		 	path.add(inputArrStart.get((int) arr.get(index).ID));
		 	
		 		while(arr.size()>1) {
				 	int destination = 0 ; 
				 	min = 999999999;
				 	int i = 0 ;
		 		while(index + i < arr.size()-1) {
		 			i++;
		 			if(i == 100)
		 				break;
		 		}
		 		for(int back = index+i  ; back >= 0;back-- ) {
		 			if(back == index ) continue;
		 			if(distance(arr.get(index),arr.get(back)) < min) {
		 					min = distance(arr.get(index),arr.get(back));
		 					destination = back;
		 				}
		 			}
		 		path.add(inputArrStart.get((int) arr.get(destination).ID));	
		 		arr.remove(index);
		 			if(destination < index)
		 		index = destination;
		 			else 
		 				index = destination-1;
		 		}
		 		arr.remove(0);
		 		return path.get(path.size()-1);
		 		
	}

	public static void mover (ArrayList<Cities> arrList ,double xtotal ,double ytotal ) {
		 for (Cities num : arrList) {
				num.setX(Math.floor(xtotal));
				num.setY(Math.floor(ytotal));
				num.setR();
			}
			Collections.sort(arrList, citiesR);
	 }

	 
	 public static Comparator<Cities> citiesR = new Comparator<Cities>() {
		 	
			public int compare(Cities s1, Cities s2) {

			   double r1 = s1.R;
			   double r2 = s2.R;

			   if(r1-r2 > 0)
				   return 1;
			   else if (r1-r2 == 0)
				   return 0 ;
			   else 
				   return -1;

		   }};
		   
	 public static int distance (Cities city1 , Cities city2) {
		 int distance = (int) Math.round((Math.sqrt(Math.pow(city1.X - city2.X, 2)+Math.pow(city1.Y-city2.Y, 2))));
		 return distance ;
	 }
}

