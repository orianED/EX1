import java.util.ArrayList;

/**
 * Created by hadar on 02/04/2019.
 */
public class Matrix {

    private ArrayList[][] m;

    public Matrix(ArrayList[][] m){
        this.m=m;
    }

    public int getColNum(){
        int length = m[0] == null ? 0 : m[0].length;
        return length;
    }

    public int getRowNum(){
        return this.m.length;
    }

    public Matrix mult(Matrix other){
        ArrayList[][] result=new ArrayList[this.getColNum()][other.getRowNum()];
        ArrayList[][] tmp= new ArrayList[this.getColNum()][other.getRowNum()];

        // Initialize newArray to be equal to array
        for (int i = 0; i < .length; i++) {
            for (int j = 0; j < array.length; j++) {
                newArray[i][j] = array[i][j];
            }
        }

        // Outer loop that multiplies as many times as you want

            for(int i=0;i<this.getRowNum();i++){
                for(int j=0;j<other.getColNum();j++){
                    int sum = 0;
                    for(int x=0;x<this.getColNum();x++) {
                        sum += this.m[i][x] * other.m[x][j];  // Use newArray here
                    }
                    }
                    tmp[i][j]=sum;
                }
            }

            // Copy the result from multiplication to newArray and restart tmp
            System.arraycopy(tmp, 0, newArray, 0, tmp.length);
            tmp = new int[array.length][array.length];
        }

        return newArray;
    }


    }
}
