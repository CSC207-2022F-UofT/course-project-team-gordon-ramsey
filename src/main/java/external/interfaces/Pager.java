package external.interfaces;

import business.rules.ui.UI;

public class Pager {
    private String[][][] data;
    private int page_size, start_index;

    public Pager(String[][][] data, int page_size){
        this.data = data;
        this.page_size = page_size;
        this.start_index = 0;
    }

    public void printPage(UI ui){
        int end_index =  Math.min(start_index + page_size, data.length);
        for(int i = start_index; i < end_index; i++){
            ui.showCollection(this.data[i]);
            ui.showCollectionDivider("" + (i + 1));
        }
        
    }

    public void nextPage(){
        if(this.hasNextPage()) this.start_index += this.page_size;
    }

    public boolean hasNextPage(){
        return this.start_index + this.page_size < this.data.length;
    }

    public void previousPage(){
        if(this.hasPreviousPage()) this.start_index -= this.page_size;
    }

    public boolean hasPreviousPage(){
        return this.start_index - this.page_size >= 0;
    }
}
