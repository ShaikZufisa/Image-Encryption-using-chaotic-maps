 package com.Image_Encryption.border;

import com.Image_Encryption.PseudoImg.*;
import com.Image_Encryption.encryption.*;

public class Border {
	
	public byte[] add(byte[] data, int n) {
		
		int row, column;
		
		row= (int)Math.sqrt(data.length);	row+= 200;
		double temp= data.length/row;
		column= (int)temp;
		
		if(temp > column)
			column++;
		
		column+= 200;
		
		PseudoImage pseudoImg= new PseudoImage();
		Shuffling shuf= new Shuffling(row, column);
		
		byte[] d= new byte[row*column];
		byte[][] enhancedImg= new byte[row][column];
		int i=0;
		for(int r=0;r<enhancedImg.length;r++) {
			for(int c=0;c<enhancedImg[0].length;c++) {
				if(r>=0 && r<100 || c>=0 && c<100 || r>=enhancedImg.length-100 && r<enhancedImg.length || c>=enhancedImg.length-100 && c<enhancedImg.length-1) {
					enhancedImg[r][c]= (byte)((Math.random()*256) - 128);
				}
				else {
					enhancedImg[r][c]= data[i];
					i++;
				}
			}
		}
		
		byte[][] s= pseudoImg.generate(row, column, n);
		byte[][] P= shuf.shuffle(enhancedImg ,s, row, column, n);
		
		//int[][] s= pseudoImg.generate(row, column, 2);
		
		i=0;
		for(int r=0;r<enhancedImg.length;r++) {
			for(int c=0;c<enhancedImg[0].length;c++) {
				d[i]= enhancedImg[r][c];
				i++;
			}
		}
		//if(n==1)
			//add(d,2);
		
		return d;
	}
} 
