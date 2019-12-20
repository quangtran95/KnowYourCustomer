package kyc.dto;

public class SearchCustomerInfoDto {
   String searchContent;
   String sortPattern;
   boolean sortAsc;

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
}
