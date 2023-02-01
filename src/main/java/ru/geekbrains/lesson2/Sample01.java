package ru.geekbrains.lesson2;

import java.util.Random;

public class Sample01 {
    private static final Random random = new Random();

    public static void main(String[] args) {

        int[] arr = ArrayUtils.prepareArray();
        SortUtils.directSort(arr);

        System.out.println();

        arr = ArrayUtils.prepareArray();
        SortUtils.insertionSort(arr);

        System.out.println();

        arr = ArrayUtils.prepareArray();
        SortUtils.quickSort(arr);

        int[] testArray = ArrayUtils.prepareArray(100000);

        long startTime = System.currentTimeMillis();
        SortUtils.directSort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время работы сортировки выбором: %d мс.\n", processingTime);
        //
        startTime = System.currentTimeMillis();
        SortUtils.insertionSort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы сортировки вставкой: %d мс.\n", processingTime);
        //
        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы быстрой сортировки: %d мс.\n", processingTime);
        //
        startTime = System.currentTimeMillis();
        SortUtils.heapsort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы пирамидальной сортировки: %d мс.\n", processingTime);

        System.out.println();
    }

    // Утилиты для сортировки
    static class SortUtils{

        // сортировка выбором
        public static void directSort(int[] array){
            for (int i = 0; i < array.length; i++){
                int min = i;
                for(int j = i + 1; j < array.length; j++){
                    if (array[j] < array[min]){
                        min = j;
                    }
                }
                if (i != min){

                    array[i] = array[i] + array[min];
                    array[min] = array[i] - array[min];
                    array[i] = array[i] - array[min];
               }
            }
        }

       static void quickSort(int[] array){
            quickSort(array, 0, array.length - 1);
        }

       // Быстрая сортировка
       public static void quickSort(int[] array, int startPosition, int endPosition){
            int leftPosition = startPosition;
            int rightPosition = endPosition;

            // Вычисляем индекс и значение опорного элемента
            int pivot = array[(startPosition + endPosition) / 2];

            do{
                    while (array[leftPosition] < pivot)
                        leftPosition++;

                    while (array[rightPosition] > pivot)
                        rightPosition--;

                    if (leftPosition <= rightPosition){
                        if (leftPosition < rightPosition){
                            int buf = array[leftPosition];
                            array[leftPosition] = array[rightPosition];
                            array[rightPosition] = buf;
                        }
                        leftPosition++;
                        rightPosition--;
                    }
            }
            while (leftPosition <= rightPosition);

            if (leftPosition < endPosition){
                quickSort(array, leftPosition, endPosition);
            }
            if(startPosition < rightPosition){
                quickSort(array, startPosition, rightPosition);
            }
        }

       // сортировка вставкой
       public static void insertionSort(int[] array) {
           for (int i = 0; i < array.length; i++) {
               for (int j = 0; j < array.length; j++) {
                   if (array[j] < array[i]){
                       int buf = array[i];
                       array[i] =  array[j];
                       array [j] = buf;
                   }
               }
           }
       }


       // Пирамидальная сортировка
       public static void heapsort(int[] array){

        //public static void heapsort(int[] a) {
                for (int i = array.length / 2 - 1; i >= 0; i--)
                    // convert the array to a heap
                    shiftDown(array, i, array.length);
                for (int i = array.length - 1; i > 0; i--) {
                    swap(array, 0, i); /* deleteMax */
                    shiftDown(array, 0, i);
                }
            } // end heapSort

            private static void shiftDown(int[] a, int i, int n) {
                int child;
                int tmp;

                for (tmp = a[i]; leftChild(i) < n; i = child) {
                    child = leftChild(i);
                    if (child != n - 1 && (a[child] < a[child + 1]))
                        child++;
                    if (tmp < a[child])
                        a[i] = a[child];
                    else
                        break;
                }
                a[i] = tmp;
            }

            private static int leftChild(int i) {
                return 2 * i + 1;
            }

            // swap numbers
            public static void swap(int[] numbers, int i, int j) {
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
    }
    // Утилиты для работы с массивом
    static class ArrayUtils{

        static int[] prepareArray(){  // создали массив маленькийй
            int[] arr = new int[random.nextInt(30)];
            for (int i = 0; i < arr.length; i++){
                arr[i] = random.nextInt(100);
            }
            return arr;
        }

        static int[] prepareArray(int length){ // создали массив большой перегруженный
            int[] arr = new int[length];
            for (int i = 0; i < arr.length; i++){
                arr[i] = random.nextInt(100);
            }
            return arr;
        }
    }
}
