package Maze;

import java.lang.reflect.Array;

public class NVector<T> {

	protected int dimension;
	
	protected int[] magnitudes;
	
	protected T[] data;
	
	public NVector(int[] values, T item){
		dimension = values.length;
		magnitudes = values;
		int size = magnitudes[0];
		for (int dim = 1; dim < magnitudes.length; dim++){
			size = size * magnitudes[dim];
		}
		data = (T[]) Array.newInstance(item.getClass(), size);
	}


	/**
	 * gives the size of the nth dimension of the vector. Starting from the first dimension (1) to the max dimension of the vector;
	 * @param n
	 * @return the value of the nth dimension of the vector if applicable. -1 otherwise.
	 */
	public int getMagnitude(int n){
		if (n <= magnitudes.length && n > 1){
			return magnitudes[n-1];
		} else {
			return -1;//Failure.
		}
	}
	
	public int getDimension(){
		return dimension;
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	public T getValue(int[] location){
		if (location.length != dimension){
			throw new IllegalArgumentException("Location must have the same dimension as the NVector");
		} else {
			int refPos = 0;
			for (int i = 0; i < dimension; i++){
				//data.length
				refPos += location[i] * sizeOfLowerDimensions(i);
			}
			return data[refPos];
		}
	}
	
	/**
	 * returns the partitionOffset of a dimension, meaning the size 
	 * necessary to hold all dimensions lower than the current.
	 * @param dim
	 * @return
	 */
	private int sizeOfLowerDimensions(int dim){
		int ret = 1;
		dim = dim + 1;
		while (dim < magnitudes.length){
			ret = ret * magnitudes[dim];
			dim++;
		}
		return ret;
	}
	
	public int[] getLocation(T item){
		int pos = 0;
		for (int i = 0; i < data.length; i++){
			if (data[i] == item){
				pos = i;
				i = data.length;
			}
		}
		int[] location = new int[dimension];
		int lowerDimensionSize = data.length;
		int lowBound = 0;
		for (int i = 0; i < dimension; i++){
			lowerDimensionSize = lowerDimensionSize / magnitudes[i];
			location[i] = (pos - lowBound)/lowerDimensionSize;
			lowBound += location[i] * lowerDimensionSize;
		}
		return location;
	}
	
	protected int[] getIntegerAsLocation(int loc){
		if (loc < data.length){
			int[] location = new int[dimension];
			int lowerDimensionSize = data.length;
			int lowBound = 0;
			for (int i = 0; i < dimension; i++){
				lowerDimensionSize = lowerDimensionSize / magnitudes[i];
				location[i] = (loc - lowBound)/lowerDimensionSize;
				lowBound += location[i] * lowerDimensionSize;
			}
			return location;
		} else {
			throw new IllegalArgumentException("The int presented is out of bounds for the data");
		}
	}

	public void setValue(T item, int[] location){
		if (location.length != dimension){
			throw new IllegalArgumentException("Location must have the same dimension as the NVector");
		} else {
			int refPos = 0;
			int lastLength = data.length;//The length of the most recent partition of the data array
			for (int i = 0; i < dimension; i++){
				lastLength = lastLength/magnitudes[i];
				refPos += lastLength * location[i];	
			}
			data[refPos] = item;
		}
	}
	
	public int getDataLength(){
		return data.length;
	}
	
}
