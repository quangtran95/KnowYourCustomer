package kyc.dto;

public class SearchCustomerInfoDto {
   String searchContent;
   String sortPattern;
   boolean sortAsc;
   int startIndex = 0;
   int limitNumber = 5;

   public String getSearchContent() {
      return searchContent;
   }

   public void setSearchContent(String searchContent) {
      this.searchContent = searchContent;
   }

   public String getSortPattern() {
      return sortPattern;
   }

   public void setSortPattern(String sortPattern) {
      this.sortPattern = sortPattern;
   }

   public boolean isSortAsc() {
      return sortAsc;
   }

   public void setSortAsc(boolean sortAsc) {
      this.sortAsc = sortAsc;
   }

   public int getStartIndex() {
      return startIndex;
   }

   public void setStartIndex(int startIndex) {
      this.startIndex = startIndex;
   }

   public int getLimitNumber() {
      return limitNumber;
   }

   public void setLimitNumber(int limitNumber) {
      this.limitNumber = limitNumber;
   }
}
