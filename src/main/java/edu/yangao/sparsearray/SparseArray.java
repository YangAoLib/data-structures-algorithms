package edu.yangao.sparsearray;

/**
 * Main
 * 二维数据与稀疏数组互相转换
 */
public class SparseArray {

    public static void main(String[] args) {
        // 生成基础二维数组
        // 0-无子, 1-黑子, 2-白子
        int[][] chessArrayOld = new int[11][11];
        chessArrayOld[0][1] = 1;
        chessArrayOld[2][4] = 2;
        chessArrayOld[3][5] = 1;

        // 输出基础二维数组
        printTwoArray("输出基础二维数组", chessArrayOld);

        // 生成稀疏数组
        // 1. 遍历二维数组 查询非0数字个数 用于生成稀疏数组
        int count = 0;
        for (int[] row : chessArrayOld) {
            for (int cell : row) {
                if (cell != 0)
                    ++count;
            }
        }
        // 创建稀疏数组
        int[][] sparseArray = new int[count + 1][3];
        // 设置第一行数组(行数, 列数, 非0数据个数)
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = count;
        // 遍历二维数组填充稀疏数组
        int currentSparseArrayRowIndex = 1; // 当前需要填充的稀疏数组的行坐标
        for (int i = 0; i < chessArrayOld.length; i++) {
            for (int j = 0; j < chessArrayOld[i].length; j++) {
                if (chessArrayOld[i][j] != 0) {
                    // 填充稀疏数组
                    sparseArray[currentSparseArrayRowIndex][0] = i; // 存储行下标
                    sparseArray[currentSparseArrayRowIndex][1] = j; // 存储列下标
                    sparseArray[currentSparseArrayRowIndex][2] = chessArrayOld[i][j]; // 存储具体数据
                    ++currentSparseArrayRowIndex; // 定位下标后移
                }
            }
        }
        // 输出稀疏数组
        printTwoArray("输出稀疏数组", sparseArray);

        // 还原二维数组
        // 创建二维数组根据稀疏数组
        int[][] chessArrayNew = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 还原非0 数据
        for (int i = 1; i < sparseArray[0][2]; i++) {
            chessArrayNew[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        printTwoArray("输出还原后的二维数组", chessArrayNew);

    }

    /**
     * 打印提示语并打印int二维数组
     *
     * @param tips  提示语
     * @param array int二维数组
     */
    private static void printTwoArray(String tips, int[][] array) {
        System.out.println(tips);
        for (int[] row : array) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }
}