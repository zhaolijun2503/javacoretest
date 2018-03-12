package org.javacore.sort;


public class Sort {
	
	private  int  a ;
	
	public  static void  maopaoSort(int[] array){
		int len = array.length;
		for (int i = 0; i < array.length-1; i++) {
			for (int j = 0; j < array.length-i-1; j++) {
				if(array[j]>array[j+1]){
					swap(array, j, j+1);
				}
			}
		}
	}
	
	public static void selectSory(int[] array){
		int len=array.length;
		for (int i = 0; i < array.length-1; i++) {
			for (int j = i+1; j < array.length; j++) {
				if(array[j]<array[i]){
					swap(array, j, i);
				}
			}
		}
	}
	
	public static void swap(int[] array,int i,int j){
		int temp=0;
		temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	
	public static void  swap3(Sort s){
		s=new Sort();
    	s.a=3;
    }
	
	public static void  swap4(int a){
		a=4;
	}

	
	public static void main(String[] args) {
		    int a=2;
		    Sort.swap4(a);
		    System.out.println(a);
			Sort  s = new Sort();
			s.a = 1 ;
			System.out.println(s.a);
        	Sort.swap3(s);
        	System.out.println(s.a);
		/*int[] array = new int[5];
		array[0]=5;
		array[1]=1;
		array[2]=2;
		array[3]=4;
		array[4]=3;
		Sort.selectSory(array);
        System.out.println(Arrays.toString(array));*/
	}
}
