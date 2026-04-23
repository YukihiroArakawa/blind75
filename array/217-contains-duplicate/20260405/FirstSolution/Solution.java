import java.util.HashSet;

class Solution {
  // hashsetを使って重複が発生したらtrueを返せば行けそう
  public boolean containsDuplicate(int[] nums) {

    HashSet seen = new HashSet();

    for (int num : nums) {

      if (seen.contains(num))
        return true;

      seen.add(num);
    }

    return false;
  }
}
