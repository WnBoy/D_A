package com.xupt.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author Wnlife
 * @create 2019-12-07 16:34
 * <p>
 * 赫夫曼编码
 */
public class HuffmanCode {

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        System.out.println("要编码的字符串为：" + content);
        byte[] bytes = content.getBytes();
        System.out.println("要编码的字节数组：" + Arrays.toString(bytes) + "长度为：" + bytes.length);

        /*List<Node> nodes = getNodes(bytes);//生成对应 的节点集合
        System.out.println("生成的节点集合:" + nodes);
        Node root = createHuffmanTree(nodes);//生成赫夫曼树
        System.out.println("前序遍历：");
        preOrder(root);//前序遍历观察
        getCodes(root);//获取编码
        System.out.println(huffmanCodes);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);//生成编码后的字节数组
        System.out.println("编码后的字节数组："+Arrays.toString(huffmanCodeBytes));*/

        byte[] huffmanCodeBytes = huffmanZip(bytes);
        System.out.println("压缩后的字节数组：" + Arrays.toString(huffmanCodeBytes) + "长度为：" + huffmanCodeBytes.length);

        byte[] decodeBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解码后的数据为:" + new String(decodeBytes));


        //文件压缩测试
//        zipFile("E:\\0.bmp", "E:\\1.zip");
//        System.out.println("压缩文件完成~~");

        //文件解压测试
//        unZipFile("E:\\1.zip","E:\\1.bmp");
//        System.out.println("解压文件完成~~");

    }


    /************************************************************文件压缩与解压**********************************************************/
    /**
     * 编写一个方法，完成对压缩文件的解压
     *
     * @param zipFile 要解压的文件的路径
     * @param dstFile 解压后的文件存放路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //创建输入流
        ObjectInputStream ois = null;
        //创建输出流
        FileOutputStream os = null;
        try {
            //将要解压的文件读入到流中
            ois = new ObjectInputStream(new FileInputStream(zipFile));
            //从流中读取编码后的字节数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //从流中读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一个文件使用赫夫曼编码进行压缩
     *
     * @param srcFile 需要压缩的文件目录
     * @param dstFile 压缩后文件存放的目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        ObjectOutputStream oos = null;
        //创建为文件输入的流
        FileInputStream is = null;
        try {
            //将文件读入到刘中
            is = new FileInputStream(srcFile);
            byte[] bytes = new byte[is.available()];
            //把文件读到字节数组中
            is.read(bytes);
            //对文件进行赫夫曼压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            //创建文件输出流，存放压缩的文件
            oos = new ObjectOutputStream(new FileOutputStream(dstFile));
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的方式写入赫夫曼编码，是为了以后我恢复源文件时使用，一定要把赫夫曼编码写入到压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*******************************************************解码***********************************************************/
    /**
     * 根绝赫夫曼编码表，对数据压缩的数据进行解压
     * 思路：
     * 1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
     * 2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." -> 对照 赫夫曼编码  -> "i like like like java do you like a java"
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码后的字节数组
     * @return 解码后的byte数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //得到huffmanBytes对应的二进制字符串，形式101010001011111111...
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(!flag, b));
        }
        //把赫夫曼编码表进行反向调换，因为需要反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //把得到的字符串根据反向的赫夫曼编码表进行解码
        List<Byte> list = new ArrayList<>();//存放解码后的byte
        //扫描sb字符串，对每个匹配到的二进制字符串进行解码
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            Byte b = null;
            boolean flag = true;
            while (flag) {
                String key = sb.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//没有匹配到
                    count++;
                } else {//匹配到
                    flag = false;
                }
            }
            i += count;
            list.add(b);
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成其对应的二进制的字符串
     *
     * @param flag 标志是否需要补高位，如果是true表示需要补高位，如果是false表示不补,
     *             如果是最后一个字节，无需补高位(原因：因为最后一个字节对应编码后的二进制字符串的最后一段，
     *             不一定长度为8，用256补完高位，这个字节对应的字符串长度会变为变成8，这是要不得的)
     * @param b    传入的byte
     * @return 是传入的b对应的二进制字符串(注意是按照补码返回)
     */
    public static String byteToBitString(boolean flag, byte b) {
        //将b转成int类型
        int temp = b;
        /**
         * 如果是正数，需要补高位
         * 原因：如果是正数，使用Integer.toBinaryString()方法得到的是无前导0的二进制字符串
         * 得到的字符串可能长度小于8，而在编码是长度小于8的是以0占据的，所以此处使用temp |= 256，
         * temp | 1 0000 0000,将temp的高位补满0，在最后转换成字符串时，只截取8位，
         * 因为我们在编码过程中，一直是用  byte=“8位二进制字符串”  一一对应的。
         */
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

/*******************************************************编码***********************************************************/
    /**
     * 赫夫曼编码
     *
     * @param bytes 要编码的字节数组
     * @return 编码后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        //生成对应 的节点集合
        List<Node> nodeList = getNodes(bytes);
        //生成赫夫曼树
        Node root = createHuffmanTree(nodeList);
        //获取编码表
        getCodes(root);
        //生成编码后的字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 根据所生成的哈夫曼编码表，对传入的字节数组进行编码，返回编码后的字节数组
     *
     * @param bytes 要编码的字节数组
     *   [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97,
     *   118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
     * @param huffmanCodes 赫夫曼编码表
     * @return 编码后的字节数组
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        //根据编码表对传入的字节数组进行编码，生成一个编码后的字符串
        //101010001011111111001000101111111100100010111111110010010100110111000111000001101
        // 1101000111100101000101111111100110001001010011011100
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
//        System.out.println(sb.toString());
        /**
         * 将编码后的二进制字符串每8位变为一个字节(byte)，放入到一个字节数组中huffmanCodeBytes
         * 例子：前8位：10101000(补码)->10101000 - 1 =10100111(反码)->11011000=-88
         */
        //计算最后生成的字节数组的长度: lem=(sb.length()+7)/8;
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //创建压缩后的子节数组的长度
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            /**
             *  将strByte转成一个byte，即每8个切割后的二进制字符串转成一个byte，放入到huffmanCodeBytes中
             *  计算机存储的都是补码，现在想得到10101000对应的byte，即对应的原码。
             *  使用Integer类中的parseInt(String s, int radix)方法转换，
             *  但是使用这个方法将8位的二进制字符串转换时，8位的二进制字符串是当做32位进行转换的，高位用0补齐，
             *  这就导致原本8位二进制字符串“10101000”最高位的1不再表示符号位，而32位的最开头补上的0才是符号位，
             *  这样转换后得到的int类型不是我们对应的byte类型，但是转换后的int类型的后八位和我们需要的byte类型的后8位是一样的，
             *  所以，可以直接使用类型转换截取后8位，得到我们想要的byte类型，即“10101000”对应的byte。
             */
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    /**
     * 生成对应的赫夫曼编码表：
     */
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    // 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void getCodes(Node root) {
        if (root == null) {
            return;
        }
        //处理哈夫曼树的左子节点
        getCodes(root.left, "0", stringBuilder);
        //处理哈夫曼树的右子节点
        getCodes(root.right, "1", stringBuilder);
    }

    /**
     * 将传入的node节点的hehuman编码存入到map中，形成一个赫夫曼编码表
     * {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     *
     * @param node 传入的节点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param sb   用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {//node为空不处理
            //判断当前节点是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //向左递归处理
                getCodes(node.left, "0", sb2);
                //向右递归处理
                getCodes(node.right, "1", sb2);
            } else {//是叶子节点,表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }

    /**
     * 前序遍历的方法
     *
     * @param root 树的根节点
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * 可以通过List创建对应的赫夫曼树
     *
     * @param nodes 节点集合
     * @return 返回赫夫曼树的根节点
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //先对原先的集合进行排序
            Collections.sort(nodes);
            //取出第一课最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已处理的两颗二叉树从集合中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 将要编码的字符串对应的字节数组变成一个Node节点的List集合，统计每个字符出现的次数
     *
     * @param bytes 要编码的字符串对应的字节数组
     * @return Node节点的List集合
     */
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> list = new ArrayList<>();
        Map<Byte, Integer> byteCounts = new HashMap<>();
        //统计每个字符出现的次数，放入到map中
        for (byte b : bytes) {
            Integer count = byteCounts.get(b);
            if (count == null) {
                byteCounts.put(b, 1);
            } else {
                byteCounts.put(b, count + 1);
            }
        }
        //将map中的元素转换为Node并存入到List中
        for (Map.Entry<Byte, Integer> entry : byteCounts.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

}

/**
 * 树的节点
 */
class Node implements Comparable<Node> {
    Byte data;//存放数据本身，比如'a' => 97 ，' ' => 32
    int weight;//权重，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;//从小到排序
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
