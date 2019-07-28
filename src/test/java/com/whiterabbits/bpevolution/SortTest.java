package com.whiterabbits.bpevolution;

import com.whiterabbits.bpevolution.config.TestConfig;
import com.whiterabbits.bpevolution.utils.Sort;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SortTest {

    @Test
    public void testDataReverseSolution(){
        int[] result = Sort.DataReverse(new int[]{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0});
        int[] expectedResult = new int[]{1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1};

        Assert.assertArrayEquals(expectedResult,result);
    }



}
