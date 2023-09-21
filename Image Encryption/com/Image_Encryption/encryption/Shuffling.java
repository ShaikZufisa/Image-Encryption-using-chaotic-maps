package com.Image_Encryption.encryption;

//import com.Project.Image.encryption.Xoring;
import java.util.*;

public class Shuffling {
	int[][] D;
	byte[][] R;
	byte[][] T;
	byte[][] ans;
	String ld= "";
	int ldBits;
	
	public Shuffling(){
		
	}
	
	public Shuffling(int row, int column) {
		D= new int[row][column];
		R= new byte[row][column];
		T= new byte[row][column];
		ans= new byte[row][column];
	}
	
	public void D(int row, int column){
		int val=1; 
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				D[r][c]= val;
				val++;
			}
		}
	}
	
	public void binary(int decimal) {
		while(decimal>0) {
			int digit= (decimal%2 ==0)? 0 : 1;
			char bit= (char) (digit + '0');
			ld= bit + ld;
			
			decimal= decimal/2;
		}
	}
	/*
	public void sortByColumn(int[][] R, int c) {
		Arrays.sort(R, new Comparator<int[]>() {
			@Override
			public int compare(final int[] entry1, final int[] entry2) {
				if(entry1[c] > entry2[c])
					return 1;
				else
					return -1;
			}
		});
	}
	*/
	public void sorted(byte[][] R) {
		Arrays.sort(R);						//sorted row-wise
		
		//sort column-wise
		//for(int c=0;c<R[0].length;c++) {
		//	sortByColumn(R, 0);
		//}
	}
	
	public byte[][] shuffle(byte[][] P, byte[][] S, int row, int column, int i){
		
		Xoring x= new Xoring(row, column);
		
		D(row, column);
		
		binary(row*column);
		ldBits= ld.length();
		
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				R[r][c]= (byte) (((int)Math.pow(2, 8+ldBits)) * S[r][c]);
				R[r][c]+= (byte) (((int)Math.pow(2, 8)) * D[r][c]);
				R[r][c]+= P[r][c];
			}
		}
		sorted(R);
		
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				T[r][c]= (byte) (R[r][c] & ((int)Math.pow(2, 8)-1));
			}
		}
		
		ans= x.xor(S, T);
		
		return ans;
	}
}