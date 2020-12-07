package dariomorgrane.RCSLabs.model;

public class RowModel {

    private final String row;
    private final String col;
    private final long val;

    public RowModel(String row, String col, long val) {
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
