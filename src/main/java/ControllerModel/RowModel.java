package ControllerModel;

/**
 * The RowModel class is model of each row of Pivot table.
 *
 * @author  Daniil Mikhailenko
 */
public class RowModel {

    private final String row;

    private final String col;

    private final int val;

    public RowModel(String row, String col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public String getRow() {
        return row;
    }

    public String getCol() {
        return col;
    }

    public long getVal() {
        return val;
    }
}