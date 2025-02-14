package com.leetcode.linked.list;

public class AuthenticationManager {
    private int timeToLive;
    private TokenNode head;

    private static class TokenNode {
        int expireTime;
        String tokenId;
        TokenNode next;

        TokenNode(String tokenId, int expireTime) {
            this.tokenId = tokenId;
            this.expireTime = expireTime;
        }
    }

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        TokenNode newHead = new TokenNode(tokenId, currentTime + timeToLive);
        newHead.next = head;
        head = newHead;
    }

    public void renew(String tokenId, int currentTime) {
        TokenNode current = head;
        TokenNode prev = null;
        while (current != null) {
            if(current.tokenId.equals(tokenId)) {
                if(current.expireTime > currentTime) {
                    generate(tokenId, currentTime);
                }
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    head.next = current.next;
                }
                return;
            } else {
                prev = current;
                current = current.next;
            }
        }

    }

    public int countUnexpiredTokens(int currentTime) {
        int i = 0;
        TokenNode current = head;
        while(current != null && current.expireTime > currentTime) {
            i++;
            current = current.next;
        }
        return i;
    }

    public static void main(String[] args) {
        //["AuthenticationManager","generate","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","countUnexpiredTokens","generate","renew","generate","countUnexpiredTokens","generate"]
        //[[671],["emebs",176],[209],[213],[225],[289],[353],[497],[664],[685],["ahz",717],["ahz",788],["aofte",887],[909],["ozekk",927]]
        AuthenticationManager authenticationManager = new AuthenticationManager(671);
        authenticationManager.generate("emebs", 176);
        System.out.println(authenticationManager.countUnexpiredTokens(209));
        System.out.println(authenticationManager.countUnexpiredTokens(213));
        System.out.println(authenticationManager.countUnexpiredTokens(225));
        System.out.println(authenticationManager.countUnexpiredTokens(289));
        System.out.println(authenticationManager.countUnexpiredTokens(353));
        System.out.println(authenticationManager.countUnexpiredTokens(497));
        System.out.println(authenticationManager.countUnexpiredTokens(664));
        System.out.println(authenticationManager.countUnexpiredTokens(685));
        authenticationManager.generate("ahz", 717);
        authenticationManager.renew("ahz", 788);
        authenticationManager.generate("aofte", 887);
        System.out.println(authenticationManager.countUnexpiredTokens(909));
        authenticationManager.generate("ozekk", 927);
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
