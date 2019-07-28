package com.whiterabbits.bpevolution.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sort {

    public static int[] sortPairCroissantImpairDecroissant(int[] values){

        //List<Integer> list = new ArrayList(Arrays.asList(values));

        List<Integer> list = new ArrayList();
        for(int i: values){
            list.add(i);
        }

        List<Integer> resultList = list.stream()
                .filter(e->e%2==0)
                .distinct()
                .sorted()
                .collect(Collectors.toList() );

        resultList.addAll(list.stream()
                .filter(e->e%2!=0)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList() ));

        //resultList.forEach(System.out::println);

        Object[] objTab = resultList.toArray();
        int[] resultTab = new int[objTab.length];

        for (int i=0;i<objTab.length;i++) {

            resultTab[i] = (int) objTab[i];
            System.out.println(resultTab[i]);
        }

/*        int cpt = 0;
        for (int i: resultList) {
            resultTab[cpt] = i;
            cpt++;
        }*/


        return resultTab;

    }

    public static int[] menFromBoys(final int[] values) {
        int arrEven [] = Arrays.stream(values).filter(a->a%2==0).distinct().sorted().toArray();
        int arrOdd [] = Arrays.stream(values).filter(a->a%2!=0).distinct().map(a->a*(-1)).sorted().map(a->a*(-1)).toArray();

        return IntStream.concat(IntStream.of(arrEven),IntStream.of(arrOdd)).toArray();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param numbers
     * @param target
     * @return index des 2 éléments de numbers dont l'addition est égale à target
     */
    public static int[] twoSum(int[] numbers, int target)
    {
        int[] result = new int[2];
        for(int i=0;i<numbers.length;i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] + numbers[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public static int[] twoSumSolution(int[] numbers, int target)
        {
            for(int i = 0; i < numbers.length; i++) {
                for(int j = i + 1; j < numbers.length; j++) {
                    if(numbers[i] + numbers[j] == target){
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        ////////////////////////////////////////////////////////////////////////////////////

    ///FAUX
    public static int[] nbMonths(int startPriceOld, int startPriceNew, int savingperMonth, double percentLossByMonth) {
        double percentLossVariation = 0.5;
        int nbMonth = 0;
        int saving = startPriceOld;


        while(saving<startPriceNew){
            nbMonth++;
            saving = (int)(startPriceOld - (startPriceOld * (percentLossByMonth/100)) + savingperMonth*nbMonth) ;
            startPriceNew =  (int) (startPriceNew - (startPriceNew * (percentLossByMonth/100)));
            if (nbMonth%2==0) percentLossByMonth+=(percentLossVariation);
        }



        saving = saving - startPriceNew;
        System.out.printf("Il lui faudra economiser %s pendant %s mois pour se payer cette voiture",saving,nbMonth);
        return new int[]{nbMonth,saving};

    }

    public static int[] nbMonthsSolution(int startPriceOld, int startPriceNew, int savingperMonth, double percentLossByMonth) {
        int month = 1;
        int savings = 0;
        double startPriceOldD = (double) startPriceOld;
        double startPriceNewD = (double) startPriceNew;
        while (startPriceNewD > (startPriceOldD + savings)) {
            if (month % 2 == 0) percentLossByMonth += .5d;
            startPriceOldD *= (1 - percentLossByMonth / 100);
            startPriceNewD *= (1 - percentLossByMonth / 100);
            savings += savingperMonth;
            month++;
        }
        return new int[]{--month, (int) Math.round((startPriceOldD + savings) - startPriceNewD)};
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int[] DataReverse(int[] data) {

        int multipleOf8 = data.length/8;
        int[] result = new int[data.length];
        int end, index = 0, round = 0;

        for(int i=0;i<multipleOf8;i++){
            round++;
            end = data.length-8*(round-1);
            System.out.println("round = " + round);
            System.out.println("end = " + end);
            for(int j=data.length-8*round;j<end;j++){
                result[index] = data[j];
                index++;
            }
        }

        for(int i : result)
            System.out.println(i);

        return result;
    }

    public static int[] DataReverseSolution(int[] data) {
        int bytes[] = new int[data.length];
        for (int i = data.length-8, j=0; i>=0; i-=8, j+=8) {
            System.arraycopy(data, i, bytes, j, 8);
        }
        return bytes;
    }

}
