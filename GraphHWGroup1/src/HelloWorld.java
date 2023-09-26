class HelloWorld {
    public static String getShortestUniqueSubstring(char[] arr, String str) {
        int[] arrCount = new int[26]; // Assuming lowercase English alphabet characters
        int[] windowCount = new int[26];

        for (char c : arr) {
            arrCount[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int uniqueCount = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < str.length()) {
            char currentChar = str.charAt(right);
            if (arrCount[currentChar - 'a'] > 0) {
                if (windowCount[currentChar - 'a'] < arrCount[currentChar - 'a']) {
                    uniqueCount++;
                }
                windowCount[currentChar - 'a']++;
            }

            System.out.println("arr length " + arr.length);
            while (uniqueCount == arr.length) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                char leftChar = str.charAt(left);
                if (arrCount[leftChar - 'a'] > 0) {
                    windowCount[leftChar - 'a']--;
                    if (windowCount[leftChar - 'a'] < arrCount[leftChar - 'a']) {
                        uniqueCount--;
                    }
                }
                left++;
            }

            right++;
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return str.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        char[] arr = {'x', 'y', 'z'};
        String str = "xyyzyzyx";
        String result = getShortestUniqueSubstring(arr, str);
        System.out.println(result);  // Output: "zyx"
    }
}
