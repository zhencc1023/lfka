package baseUntil;

import java.util.BitSet;

public class SampleBoolmFilter {

    private static final int Bit_Size=2<<28;//二进制向量,约存储1000万条数据

    private static  final  int[] seeds=new int[]{3,5,7,11,13,31,37,61};//生成指纹信息,质数

    private BitSet bits=new BitSet(Bit_Size);

    private Hash[]  funcs=new Hash[seeds.length];//存储哈希对象

    public SampleBoolmFilter(){
        for (int i=0;i<seeds.length;i++){
            funcs[i]=new Hash(Bit_Size,seeds[i]);
        }
    }

    /**
     * 添加元素
     * @param value
     */
    public void addV(String value){
        if(value!=null){
            for(Hash f:funcs){//将元素哈希8次的结果存储bits,将整数的bit上变为1表示存在==true
                bits.set(f.hash(value),true);
            }
        }
    }

    /**
     * p判断元素是否存在
     * @param value
     * @return
     */
    public boolean contains(String value){
        if(value==null){
            return  false;
        }

        boolean res=true;
        for (Hash f:funcs)
            res=res&&bits.get(f.hash(value));//多次哈希的结果bit位全部存在，即存在

        return  res;
    }


public static  class  Hash{
    private int size;//二进制向量数组大小
    private int seeds;//随机种子数

    public Hash(int  cap,int seeds){
        this.size=cap;
        this.seeds=seeds;
    }

    /**
     * 计算哈希值，算法很多，可随意选用
     */
    public int hash(String  value){
        int result=0;
        int len=value.length();
        for (int i=0;i<len;i++){
            result=seeds*result+value.charAt(i);
        }
        return (size-1)&result;
    }
}


    public static void main(String[] args) {
        SampleBoolmFilter boolmFilter=new SampleBoolmFilter();
        boolmFilter.addV("weiwei");
        boolmFilter.addV("阵阵");
        System.out.println(boolmFilter.contains("wei阵"));
    }
}
