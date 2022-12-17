/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.exercise32_15;

/**
 *
 * @author jswai
 */
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Exercise32_15 {

	public static void main(String[] args) {
		double[] list = new double[9000000];
		for (int i = 0; i < list.length; i++) {
			list[i] = Math.random();
		}
		System.out.print(parallelSum(list));
	}//create double list and for loop

	public static double parallelSum(double[] list) {
		RecursiveTask<Double> task = new SumTask(list, 0, list.length);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}
	
	private static class SumTask extends RecursiveTask<Double> {
		private static final long serialVersionUID = 1L;
		private final static int THRESHOLD = 1000;
		private double[] list;
		private int low;
		private int high;

		public SumTask(double[] list, int low, int high) {
			this.list = list;
			this.low = low;
			this.high = high;
		}
		
		@Override
		protected Double compute() {
			if (high - low < THRESHOLD) {
				double sum = 0;
				for (int i = low; i < high; i++) {
					sum += list[i];
				}
				return sum;
			} else {
				int mid = (low + high) / 2;
				RecursiveTask<Double> left = new SumTask(list, low, mid);
				RecursiveTask<Double> right = new SumTask(list, mid, high);

				right.fork();
				left.fork();
				return left.join() + right.join();
			}
		}

	}
}