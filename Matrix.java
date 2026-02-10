package Algorithm;

public class Matrix {
    private int[][] matrix;
    public Matrix(){
        matrix=new int[0][0];
    }
    public Matrix(int row,int col){
        matrix=new int[row][col];
    }
    public Matrix(int[][] matrix){
        this.matrix=matrix;
    }
    public int[][] getMatrix(){
        return matrix;
    }
    public void setMatrix(int[][] matrix){
        this.matrix=matrix;
    }
    public void setMatrix(int row,int col,int value){
        matrix[row][col]=value;
    }
    public int getMatrix(int row,int col){
        return matrix[row][col];
    }
    public static Matrix multiply(Matrix a,Matrix b){
        if(a.matrix[0].length!=b.matrix.length){
            return null;
        }
        int[][] matrix=new int[a.matrix.length][b.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<b.matrix[0].length;j++){
                for(int k=0;k<a.matrix[0].length;k++){
                    matrix[i][j]+=a.matrix[i][k]*b.matrix[k][j];
                }
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix add(Matrix a,Matrix b){
        if(a.matrix.length!=b.matrix.length||a.matrix[0].length!=b.matrix[0].length){
            return null;
        }
        int[][] matrix=new int[a.matrix.length][a.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix[i][j]=a.matrix[i][j]+b.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix subtract(Matrix a,Matrix b){
        if(a.matrix.length!=b.matrix.length||a.matrix[0].length!=b.matrix[0].length){
            return null;
        }
        int[][] matrix=new int[a.matrix.length][a.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix[i][j]=a.matrix[i][j]-b.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix transpose(Matrix a){
        if(a.matrix.length==0||a.matrix[0].length==0){
            return null;
        }
        int[][] matrix=new int[a.matrix[0].length][a.matrix.length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix[j][i]=a.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix multiply(Matrix a,int b){

        int[][] matrix=new int[a.matrix.length][a.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix[i][j]=a.matrix[i][j]*b;
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix divide(Matrix a,int b){
        int[][] matrix=new int[a.matrix.length][a.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix[i][j]=a.matrix[i][j]/b;
            }
        }
        return new Matrix(matrix);
    }
    public static int det(Matrix a){
        if(a.matrix.length!=a.matrix[0].length){
            throw new IllegalArgumentException("Matrix is not square");
        }
        int det=0;
        if(a.matrix.length==1){
            return a.matrix[0][0];
        }
        if(a.matrix.length==2){
            return a.matrix[0][0]*a.matrix[1][1]-a.matrix[0][1]*a.matrix[1][0];
        }
        for(int i=0;i<a.matrix.length;i++){
            Matrix b=new Matrix(a.matrix.length-1,a.matrix[0].length-1);
            for(int j=1;j<a.matrix.length;j++){
                for(int k=0;k<a.matrix[0].length;k++){
                    if(k<i){
                        b.setMatrix(j-1,k,a.matrix[j][k]);
                    }
                    else if(k>i){
                        b.setMatrix(j-1,k-1,a.matrix[j][k]);
                    }
                }
            }
            det+=a.matrix[0][i]*Math.pow(-1,i)*det(b);
        }
        return det;
    }
    public static Matrix inverse(Matrix a){
        if(a.matrix.length!=a.matrix[0].length){
            throw new IllegalArgumentException("Matrix is not square");
        }
        int det=det(a);
        if(det==0){
            return null;
        }
        int[][] matrix=new int[a.matrix.length][a.matrix[0].length];
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                Matrix b=new Matrix(a.matrix.length-1,a.matrix[0].length-1);
                for(int k=0;k<a.matrix.length;k++){
                    for(int l=0;l<a.matrix[0].length;l++){
                        if(k!=i&&l!=j){
                            b.setMatrix(k<j?k:k-1,l<j?l:l-1,a.matrix[k][l]);
                        }
                    }
                }
                matrix[j][i]= (int) (Math.pow(-1,i+j)*det(b));
            }
        }
        return new Matrix(matrix);
    }
    public static Matrix power(Matrix a,int b){
        Matrix matrix=new Matrix(a.matrix.length,a.matrix[0].length);
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix.setMatrix(i,j,1);
            }
        }
        for(int i=0;i<b;i++){
            matrix=multiply(matrix,a);
        }
        return matrix;
    }
    public static Matrix power(Matrix a,Matrix b){
        Matrix matrix=new Matrix(a.matrix.length,a.matrix[0].length);
        for(int i=0;i<a.matrix.length;i++){
            for(int j=0;j<a.matrix[0].length;j++){
                matrix.setMatrix(i,j,1);
            }
        }
        for(int i=0;i<b.matrix[0][0];i++){
            matrix=multiply(matrix,a);
        }
        return matrix;
    }
    public static Matrix identity(int n){
        int[][] matrix=new int[n][n];
        for(int i=0;i<n;i++){
            matrix[i][i]=1;
        }
        return new Matrix(matrix);
    }
    public static Matrix zero(int n){
        int[][] matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=0;
            }
        }
        return new Matrix(matrix);
    }
    public int getRow(){
        return matrix.length;
    }
    public int getCol(){
        return matrix[0].length;
    }
    @Override
    public String toString(){
        String s="";
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                s+=matrix[i][j]+" ";
            }
            s+="\n";
        }
        return s;
    }
    @Override
    public Matrix clone(){
        int i,j;
        int[][] matrix=new int[this.matrix.length][this.matrix[0].length];
        for(i=0;i<this.matrix.length;i++){
            for(j=0;j<this.matrix[0].length;j++){
                matrix[i][j]=this.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }
    @Override
    public boolean equals(Object a){
        if(a instanceof Matrix){
            Matrix b=(Matrix) a;
            if(b.matrix.length!=matrix.length||b.matrix[0].length!=matrix[0].length){
                return false;
            }
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    if(b.matrix[i][j]!=matrix[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
