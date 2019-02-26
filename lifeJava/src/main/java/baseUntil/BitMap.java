package baseUntil;

import java.util.BitSet;

/**
 * @author cc
 * @description 位图结构简单创建
 */
public class BitMap {



    /**
     * 创建数组
     */
    public byte[] bits;


    public void consoleBytes(byte b){
        byte[] array = new byte[8];
        for(int i = 7; i >= 0; i--){
            array[i] = (byte)(b & 1);
            b = (byte)(b >> 1);
        }

        for (byte b1 : array) {
            System.out.print(b1);
            System.out.print(" ");
        }

        System.out.println();
    }

    /**

     * 初始化构造执行byte数组扩容
     * @param num
     */
    public  BitMap(int  num){
        bits=new byte[getIndex(num)+1];
        for(int i = 0; i < num; i++){
            add(i);
        }
        int index = 1;
        for(byte bit : bits){
            System.out.println("-------" + index++ + "-------");
            consoleBytes(bit);
        }
    }

    /**
     * 一个byte=8个bit，所以除8，取到num在byte数组的位置
     * @param num
     * @return
     */
    public int getIndex(int num){
        return  num>>3;
    }


    /**
     * num%8得到在byte[index]的位置
     * @param num
     * @return
     */
    public int getPosition(int num){
        return num & 0x07;
    }

    /**
     * bitmap中添加数据num，标记type中的bit为1，证明存在
     * 将1左移指定位长，然后与当前的byte做| ,则当前位置替换为1
     * @param num
     */
    public  void add(int num){
        int idx=getIndex(num);
        int po=getPosition(num);
        System.out.println(bits[idx]);
        bits[idx]|=1<<po;
    }

    /**
     * 判断指定数字num是否存
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     * @param bits
     * @param num
     * @return
     */
    public boolean contains(int num){
        return (bits[getIndex(num)] & 1 << getPosition(num)) != 0;
    }

    /**
     * 重置某一数字对应在bitmap中的值
     * 对1进行左移，然后取反，最后与byte[index]作与操作。
     * @param bits
     * @param num
     */
    public void clear( int num){
        bits[getIndex(num)] &= ~(1 << getPosition(num));
    }

    public static void main(String[] args) {
        BitMap bt=new BitMap(100);
        System.out.println(bt.contains(101));
    }
}
