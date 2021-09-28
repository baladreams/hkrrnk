import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// solution for https://www.hackerrank.com/challenges/queens-attack-2/problem

class QueenAttacksAgain {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER n 2. INTEGER k 3. INTEGER r_q 4. INTEGER c_q 5.
     * 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here
        int possiblePositions = 0;
        possiblePositions += n - 1;
        possiblePositions += n - 1;
        possiblePositions += Math.min((r_q - 1), (c_q - 1));
        possiblePositions += Math.min((n - r_q), (n - c_q));
        possiblePositions += Math.min((n - r_q), (c_q - 1));
        possiblePositions += Math.min((r_q - 1), (n - c_q));
        boolean found_top_left = false;
        int r_tl = 0;
        int c_tl = 0;
        boolean found_bottom_left = false;
        int r_bl = 0;
        int c_bl = 0;
        boolean found_top_right = false;
        int r_tr = 0;
        int c_tr = 0;
        boolean found_bottom_right = false;
        int r_br = 0;
        int c_br = 0;
        for (List<Integer> obstacle : obstacles) {
            int r_o = obstacle.get(0);
            int c_o = obstacle.get(1);
            if (r_o == r_q)
                possiblePositions -= ((c_o > c_q) ? n - c_o : c_o - 1) + 1;
            else if (c_o == c_q)
                possiblePositions -= ((r_o > r_q) ? n - r_o : r_o - 1) + 1;
            else if (Math.abs(c_o - c_q) == Math.abs(r_o - r_q)) {
                if (c_o < c_q && r_o < r_q) {
                    if (!found_bottom_left || r_o > r_bl) {
                        r_bl = r_o;
                        c_bl = c_o;
                    }
                    found_bottom_left = true;
                }
                if (c_o < c_q && r_o > r_q) {
                    if (!found_top_left || r_o < r_tl) {
                        r_tl = r_o;
                        c_tl = c_o;
                    }
                    found_top_left = true;
                }
                if (c_o > c_q && r_o < r_q) {
                    if (!found_bottom_right || r_o > r_br) {
                        r_br = r_o;
                        c_br = c_o;
                    }
                    found_bottom_right = true;
                }
                if (c_o > c_q && r_o > r_q) {
                    if (!found_top_right || r_o < r_tr) {
                        r_tr = r_o;
                        c_tr = c_o;
                    }
                    found_top_right = true;
                }
            }
        }
        if (found_bottom_left)
            possiblePositions -= Math.min((r_bl - 1), (c_bl - 1)) + 1;
        if (found_top_left)
            possiblePositions -= Math.min((n - r_tl), (c_tl - 1)) + 1;
        if (found_bottom_right)
            possiblePositions -= Math.min((r_br - 1), (n - c_br)) + 1;
        if (found_top_right)
            possiblePositions -= Math.min((n - r_tr), (n - c_tr)) + 1;
        return possiblePositions;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        // 9
        List<List<Integer>> obstacles = new ArrayList<>();
        System.out.println(queensAttack(4, 0, 4, 4, obstacles));
        // 10
        obstacles = new ArrayList<>();
        obstacles.add(arrayToList(new int[] { 5, 5 }));
        obstacles.add(arrayToList(new int[] { 4, 2 }));
        obstacles.add(arrayToList(new int[] { 2, 3 }));
        System.out.println(queensAttack(5, 3, 4, 3, obstacles));
        // 0
        obstacles = new ArrayList<>();
        System.out.println(queensAttack(1, 0, 1, 1, obstacles));
        // 1771
        int[][] blocks = new int[][] { { 619, 264 }, { 609, 274 }, { 809, 74 }, { 819, 64 }, { 714, 264 }, { 714, 74 },
                { 952, 569 }, { 623, 471 }, { 256, 985 }, { 934, 419 }, { 745, 280 }, { 926, 189 }, { 159, 859 },
                { 106, 912 }, { 509, 894 }, { 170, 402 }, { 476, 238 }, { 173, 546 }, { 674, 438 }, { 539, 597 },
                { 215, 724 }, { 675, 975 }, { 128, 814 }, { 789, 162 }, { 492, 142 }, { 694, 384 }, { 206, 253 },
                { 347, 625 }, { 491, 147 }, { 243, 639 }, { 408, 401 }, { 850, 293 }, { 310, 901 }, { 925, 147 },
                { 138, 471 }, { 610, 203 }, { 571, 658 }, { 724, 762 }, { 456, 908 }, { 710, 842 }, { 203, 321 },
                { 972, 254 }, { 527, 214 }, { 945, 750 }, { 103, 561 }, { 374, 947 }, { 439, 716 }, { 958, 504 },
                { 830, 182 }, { 902, 635 }, { 901, 158 }, { 804, 532 }, { 903, 957 }, { 126, 916 }, { 418, 485 },
                { 493, 385 }, { 410, 247 }, { 217, 712 }, { 936, 340 }, { 805, 645 }, { 940, 430 }, { 194, 148 },
                { 719, 247 }, { 872, 928 }, { 251, 231 }, { 136, 240 }, { 609, 860 }, { 401, 230 }, { 168, 759 },
                { 531, 907 }, { 546, 927 }, { 729, 590 }, { 648, 912 }, { 407, 793 }, { 917, 256 }, { 671, 489 },
                { 926, 874 }, { 438, 467 }, { 602, 462 }, { 239, 328 }, { 104, 510 }, { 260, 739 }, { 864, 741 },
                { 837, 493 }, { 418, 345 }, { 798, 754 }, { 841, 710 }, { 169, 365 }, { 954, 389 }, { 160, 496 },
                { 183, 108 }, { 741, 796 }, { 753, 682 }, { 159, 324 }, { 417, 306 }, { 261, 509 }, { 587, 194 },
                { 805, 907 }, { 218, 204 }, { 591, 389 }, { 916, 894 }, { 963, 103 }, { 461, 689 }, { 937, 520 },
                { 350, 730 }, { 436, 208 }, { 381, 617 }, { 546, 361 }, { 719, 512 }, { 305, 794 }, { 509, 820 },
                { 915, 570 }, { 408, 420 }, { 609, 973 }, { 872, 710 }, { 465, 409 }, { 459, 427 }, { 918, 847 },
                { 142, 943 }, { 457, 364 }, { 360, 273 }, { 276, 289 }, { 427, 537 }, { 317, 475 }, { 864, 832 },
                { 845, 639 }, { 463, 520 }, { 741, 617 }, { 762, 734 }, { 160, 804 }, { 192, 739 }, { 289, 715 },
                { 340, 826 }, { 827, 872 }, { 186, 836 }, { 967, 639 }, { 145, 137 }, { 581, 147 }, { 479, 897 },
                { 203, 960 }, { 967, 691 }, { 891, 680 }, { 593, 651 }, { 172, 257 }, { 258, 325 }, { 351, 859 },
                { 153, 801 }, { 705, 589 }, { 832, 832 }, { 718, 864 }, { 971, 652 }, { 567, 561 }, { 715, 729 },
                { 689, 821 }, { 178, 891 }, { 962, 408 }, { 829, 639 }, { 561, 413 }, { 401, 261 }, { 491, 952 },
                { 831, 741 }, { 271, 471 }, { 826, 312 }, { 482, 317 }, { 413, 769 }, { 231, 561 }, { 579, 735 },
                { 769, 916 }, { 184, 586 }, { 820, 208 }, { 476, 298 }, { 587, 157 }, { 194, 642 }, { 341, 647 },
                { 478, 731 }, { 951, 620 }, { 401, 912 }, { 837, 358 }, { 249, 349 }, { 158, 201 }, { 195, 968 },
                { 294, 132 }, { 291, 193 }, { 158, 480 }, { 849, 761 }, { 832, 157 }, { 364, 809 }, { 293, 526 },
                { 164, 865 }, { 193, 429 }, { 158, 520 }, { 342, 574 }, { 409, 815 }, { 627, 765 }, { 413, 316 },
                { 426, 508 }, { 902, 620 }, { 278, 385 }, { 587, 342 }, { 982, 812 }, { 753, 721 }, { 176, 192 },
                { 289, 729 }, { 845, 729 }, { 698, 205 }, { 597, 378 }, { 570, 251 }, { 853, 742 }, { 903, 126 },
                { 546, 172 }, { 792, 491 }, { 160, 926 }, { 321, 597 }, { 835, 356 }, { 894, 207 }, { 153, 807 },
                { 106, 372 }, { 716, 105 }, { 638, 281 }, { 814, 597 }, { 970, 926 }, { 175, 943 }, { 135, 642 },
                { 372, 142 }, { 701, 980 }, { 720, 184 }, { 695, 491 }, { 682, 354 }, { 371, 246 }, { 421, 574 },
                { 803, 108 }, { 429, 687 }, { 465, 953 }, { 157, 385 }, { 624, 893 }, { 201, 896 }, { 975, 295 },
                { 635, 348 }, { 351, 659 }, { 127, 109 }, { 705, 852 }, { 471, 327 }, { 915, 750 }, { 720, 637 },
                { 126, 358 }, { 506, 690 }, { 317, 923 }, { 285, 725 }, { 957, 406 }, { 372, 810 }, { 971, 170 },
                { 920, 452 }, { 756, 743 }, { 791, 158 }, { 698, 835 }, { 576, 470 }, { 209, 329 }, { 902, 948 },
                { 854, 249 }, { 347, 780 }, { 386, 215 }, { 713, 130 }, { 516, 760 }, { 964, 374 }, { 781, 340 },
                { 763, 402 }, { 408, 824 }, { 476, 642 }, { 752, 320 }, { 605, 185 }, { 231, 745 }, { 941, 962 },
                { 354, 760 }, { 927, 426 }, { 340, 164 }, { 809, 720 }, { 465, 152 }, { 487, 280 }, { 386, 690 },
                { 401, 605 }, { 351, 517 }, { 421, 120 }, { 571, 870 }, { 409, 581 }, { 647, 268 }, { 254, 168 },
                { 290, 687 }, { 982, 452 }, { 184, 813 }, { 942, 726 }, { 829, 856 }, { 305, 874 }, { 759, 456 },
                { 623, 930 }, { 813, 984 }, { 297, 432 }, { 609, 397 }, { 257, 921 }, { 320, 375 }, { 109, 570 },
                { 126, 210 }, { 763, 189 }, { 798, 708 }, { 803, 368 }, { 971, 631 }, { 967, 791 }, { 962, 850 },
                { 906, 201 }, { 849, 403 }, { 905, 389 }, { 284, 231 }, { 726, 275 }, { 742, 562 }, { 754, 948 },
                { 724, 481 }, { 903, 168 }, { 523, 231 }, { 735, 452 }, { 930, 149 }, { 503, 950 }, { 572, 473 },
                { 561, 967 }, { 296, 281 }, { 352, 283 }, { 638, 856 }, { 760, 602 }, { 801, 170 }, { 598, 417 },
                { 841, 965 }, { 479, 485 }, { 859, 916 }, { 293, 248 }, { 457, 791 }, { 238, 462 }, { 725, 943 },
                { 821, 372 }, { 543, 901 }, { 473, 507 }, { 982, 723 }, { 726, 394 }, { 418, 350 }, { 834, 150 },
                { 218, 437 }, { 584, 290 }, { 934, 271 }, { 294, 895 }, { 543, 671 }, { 623, 784 }, { 485, 478 },
                { 276, 643 }, { 513, 136 }, { 957, 407 }, { 621, 340 }, { 972, 817 }, { 971, 291 }, { 950, 315 },
                { 674, 315 }, { 451, 982 }, { 327, 829 }, { 294, 154 }, { 732, 601 }, { 739, 352 }, { 529, 495 },
                { 240, 720 }, { 918, 387 }, { 597, 715 }, { 186, 716 }, { 824, 875 }, { 190, 852 }, { 524, 328 },
                { 531, 753 }, { 392, 985 }, { 164, 849 }, { 715, 864 }, { 438, 935 }, { 591, 946 }, { 528, 690 },
                { 296, 560 }, { 210, 102 }, { 458, 148 }, { 513, 615 }, { 561, 324 }, { 594, 451 }, { 546, 159 },
                { 506, 473 }, { 367, 107 }, { 172, 230 }, { 651, 629 }, { 345, 628 }, { 627, 425 }, { 973, 940 },
                { 923, 914 }, { 603, 437 }, { 986, 684 }, { 679, 230 }, { 560, 204 }, { 102, 578 }, { 154, 863 },
                { 241, 153 }, { 107, 152 }, { 109, 530 }, { 154, 849 }, { 610, 175 }, { 753, 213 }, { 852, 279 },
                { 328, 287 }, { 952, 832 }, { 487, 904 }, { 869, 519 }, { 915, 809 }, { 704, 657 }, { 843, 871 },
                { 371, 351 }, { 645, 963 }, { 925, 638 }, { 692, 623 }, { 492, 786 }, { 692, 708 }, { 945, 658 },
                { 176, 736 }, { 369, 547 }, { 578, 812 }, { 873, 763 }, { 620, 418 }, { 589, 209 }, { 906, 418 },
                { 426, 976 }, { 120, 451 }, { 475, 735 }, { 831, 746 }, { 908, 270 }, { 246, 721 }, { 541, 120 },
                { 352, 286 }, { 128, 826 }, { 210, 431 }, { 865, 687 }, { 817, 576 }, { 139, 907 }, { 674, 491 },
                { 671, 396 }, { 536, 604 }, { 809, 396 }, { 284, 817 }, { 941, 841 }, { 712, 370 }, { 649, 931 },
                { 395, 689 }, { 429, 249 }, { 869, 218 }, { 370, 730 }, { 570, 140 }, { 475, 136 }, { 408, 509 },
                { 958, 294 }, { 874, 847 }, { 362, 807 }, { 401, 301 }, { 892, 204 }, { 874, 365 }, { 549, 780 },
                { 487, 146 }, { 432, 293 }, { 270, 901 }, { 968, 891 }, { 386, 642 }, { 812, 801 }, { 834, 860 },
                { 987, 237 }, { 568, 894 }, { 562, 271 }, { 189, 704 }, { 230, 928 }, { 501, 905 }, { 870, 189 },
                { 980, 421 }, { 258, 126 }, { 960, 402 }, { 531, 852 }, { 450, 374 }, { 327, 840 }, { 172, 405 },
                { 960, 680 }, { 279, 532 }, { 284, 829 }, { 230, 701 }, { 289, 218 }, { 423, 580 }, { 865, 895 },
                { 635, 129 }, { 831, 809 }, { 934, 568 }, { 234, 901 }, { 639, 976 }, { 265, 256 }, { 347, 194 },
                { 204, 863 }, { 275, 748 }, { 274, 973 }, { 653, 793 }, { 854, 873 }, { 741, 917 }, { 981, 653 },
                { 194, 642 }, { 897, 538 }, { 542, 914 }, { 579, 701 }, { 983, 459 }, { 342, 857 }, { 398, 650 },
                { 139, 973 }, { 214, 216 }, { 240, 684 }, { 135, 529 }, { 128, 780 }, { 638, 493 }, { 513, 240 },
                { 428, 168 }, { 615, 423 }, { 317, 560 }, { 821, 791 }, { 973, 724 }, { 431, 729 }, { 521, 267 },
                { 740, 231 }, { 519, 706 }, { 273, 897 }, { 269, 902 }, { 284, 362 }, { 859, 912 }, { 178, 352 },
                { 432, 513 }, { 250, 248 }, { 368, 614 }, { 527, 319 }, { 341, 125 }, { 481, 543 }, { 932, 412 },
                { 398, 428 }, { 237, 648 }, { 698, 870 }, { 760, 352 }, { 279, 749 }, { 194, 607 }, { 972, 973 },
                { 865, 136 }, { 198, 750 }, { 312, 871 }, { 793, 159 }, { 985, 923 }, { 913, 635 }, { 726, 813 },
                { 940, 948 }, { 294, 364 }, { 913, 482 }, { 239, 127 }, { 457, 390 }, { 407, 679 }, { 820, 327 },
                { 372, 867 }, { 238, 794 }, { 637, 546 }, { 374, 857 }, { 416, 130 }, { 650, 650 }, { 306, 926 },
                { 810, 823 }, { 431, 378 }, { 562, 593 }, { 931, 715 }, { 925, 256 }, { 831, 718 }, { 132, 837 },
                { 153, 571 }, { 613, 436 }, { 712, 817 }, { 819, 527 }, { 497, 947 }, { 917, 629 }, { 648, 548 },
                { 835, 164 }, { 854, 324 }, { 829, 859 }, { 970, 694 }, { 478, 860 }, { 528, 204 }, { 397, 925 },
                { 126, 457 }, { 735, 593 }, { 910, 532 }, { 967, 930 }, { 462, 718 }, { 932, 867 }, { 712, 827 },
                { 794, 534 }, { 546, 162 }, { 213, 941 }, { 750, 980 }, { 487, 761 }, { 219, 263 }, { 139, 870 },
                { 573, 932 }, { 194, 609 }, { 278, 839 }, { 201, 305 }, { 159, 576 }, { 250, 857 }, { 350, 607 },
                { 350, 674 }, { 386, 702 }, { 835, 561 }, { 361, 817 }, { 974, 187 }, { 194, 984 }, { 257, 201 },
                { 846, 973 }, { 597, 361 }, { 745, 658 }, { 192, 397 }, { 463, 738 }, { 935, 913 }, { 167, 572 },
                { 324, 210 }, { 178, 641 }, { 436, 890 }, { 705, 795 }, { 548, 721 }, { 745, 104 }, { 450, 612 },
                { 106, 613 }, { 796, 908 }, { 586, 374 }, { 410, 827 }, { 348, 561 }, { 867, 573 }, { 371, 493 },
                { 951, 438 }, { 130, 302 }, { 367, 156 }, { 635, 926 }, { 912, 346 }, { 596, 732 }, { 163, 850 },
                { 495, 725 }, { 935, 849 }, { 617, 789 }, { 254, 952 }, { 592, 748 }, { 598, 514 }, { 869, 215 },
                { 503, 906 }, { 856, 865 }, { 671, 893 }, { 203, 298 }, { 618, 168 }, { 610, 207 }, { 150, 634 },
                { 159, 906 }, { 318, 867 }, { 413, 412 }, { 985, 134 }, { 180, 280 }, { 403, 781 }, { 462, 370 },
                { 895, 567 }, { 431, 876 }, { 702, 583 }, { 536, 514 }, { 518, 364 }, { 467, 697 }, { 872, 853 },
                { 435, 245 }, { 420, 134 }, { 746, 109 }, { 329, 278 }, { 432, 389 }, { 156, 875 }, { 981, 312 },
                { 876, 361 }, { 417, 269 }, { 670, 762 }, { 180, 405 }, { 270, 319 }, { 846, 345 }, { 839, 863 },
                { 524, 418 }, { 923, 704 }, { 523, 489 }, { 561, 913 }, { 723, 369 }, { 156, 620 }, { 519, 164 },
                { 689, 354 }, { 754, 324 }, { 795, 459 }, { 531, 931 }, { 761, 187 }, { 280, 289 }, { 130, 907 },
                { 340, 173 }, { 174, 846 }, { 307, 456 }, { 409, 729 }, { 129, 418 }, { 925, 465 }, { 684, 563 },
                { 932, 582 }, { 350, 471 }, { 496, 547 }, { 825, 875 }, { 306, 245 }, { 130, 678 }, { 932, 264 },
                { 180, 109 }, { 140, 520 }, { 937, 247 }, { 648, 954 }, { 423, 957 }, { 578, 254 }, { 917, 514 },
                { 382, 539 }, { 406, 405 }, { 354, 486 }, { 182, 392 }, { 547, 124 }, { 245, 760 }, { 574, 187 },
                { 413, 618 }, { 906, 976 }, { 709, 931 }, { 637, 891 }, { 759, 253 }, { 307, 684 }, { 215, 168 },
                { 427, 683 }, { 751, 132 }, { 924, 182 }, { 149, 528 }, { 794, 564 }, { 305, 386 }, { 350, 497 },
                { 215, 963 }, { 841, 102 }, { 829, 130 }, { 641, 149 }, { 185, 527 }, { 402, 804 }, { 840, 740 },
                { 635, 641 }, { 896, 573 }, { 916, 987 }, { 149, 209 }, { 408, 904 }, { 379, 528 }, { 243, 863 },
                { 319, 721 }, { 923, 942 }, { 285, 523 }, { 746, 429 }, { 609, 645 }, { 538, 528 }, { 265, 321 },
                { 502, 615 }, { 314, 432 }, { 316, 349 }, { 312, 493 }, { 694, 260 }, { 908, 541 }, { 950, 630 },
                { 482, 234 }, { 760, 548 }, { 963, 345 }, { 675, 569 }, { 628, 591 }, { 509, 485 }, { 832, 610 },
                { 761, 480 }, { 486, 841 }, { 153, 841 }, { 902, 750 }, { 204, 905 }, { 894, 539 }, { 608, 137 },
                { 156, 721 }, { 146, 435 }, { 345, 647 }, { 736, 239 }, { 987, 346 }, { 247, 319 }, { 752, 568 },
                { 597, 796 }, { 378, 180 }, { 872, 601 }, { 816, 853 }, { 489, 583 }, { 591, 506 }, { 764, 603 },
                { 963, 261 }, { 964, 702 }, { 569, 910 }, { 817, 649 }, { 841, 487 }, { 146, 609 }, { 453, 468 },
                { 914, 684 }, { 721, 216 }, { 261, 703 }, { 736, 687 }, { 369, 927 }, { 720, 640 }, { 829, 741 },
                { 671, 987 }, { 934, 248 }, { 105, 368 }, { 987, 714 }, { 973, 672 }, { 890, 840 }, { 906, 786 },
                { 134, 351 }, { 250, 394 }, { 457, 540 }, { 289, 237 }, { 537, 104 }, { 837, 437 }, { 148, 504 },
                { 409, 654 }, { 485, 895 }, { 804, 706 }, { 816, 943 }, { 234, 156 }, { 149, 206 }, { 340, 542 },
                { 840, 691 }, { 652, 514 }, { 526, 942 }, { 732, 347 }, { 280, 541 }, { 806, 521 }, { 523, 629 },
                { 835, 359 }, { 176, 702 }, { 370, 732 }, { 975, 586 }, { 674, 857 }, { 852, 364 }, { 869, 376 },
                { 719, 591 }, { 182, 469 }, { 175, 132 }, { 691, 417 }, { 124, 294 }, { 103, 809 }, { 912, 214 },
                { 597, 203 }, { 549, 385 }, { 896, 970 }, { 463, 691 }, { 508, 701 }, { 360, 183 }, { 650, 471 },
                { 169, 249 }, { 824, 198 }, { 734, 312 }, { 679, 308 }, { 927, 513 }, { 261, 384 }, { 873, 451 },
                { 876, 972 }, { 265, 604 }, { 746, 594 }, { 652, 863 }, { 127, 608 }, { 692, 493 }, { 675, 958 },
                { 564, 584 }, { 742, 206 }, { 816, 563 }, { 906, 209 }, { 931, 694 }, { 608, 203 }, { 580, 405 },
                { 631, 315 }, { 129, 950 }, { 596, 496 }, { 826, 583 }, { 365, 928 }, { 842, 902 }, { 508, 497 },
                { 903, 127 }, { 381, 916 }, { 458, 721 }, { 685, 469 }, { 720, 875 }, { 351, 319 }, { 178, 296 },
                { 830, 740 }, { 547, 365 }, { 823, 679 }, { 507, 950 }, { 297, 395 }, { 217, 460 }, { 186, 354 },
                { 405, 402 }, { 245, 641 }, { 975, 153 }, { 758, 604 }, { 390, 172 }, { 936, 672 }, { 823, 271 },
                { 590, 462 }, { 470, 302 }, { 680, 837 }, { 371, 463 }, { 605, 560 }, { 248, 172 }, { 856, 813 },
                { 152, 795 }, { 254, 739 }, { 705, 285 }, { 576, 968 }, { 270, 214 }, { 539, 793 }, { 376, 560 },
                { 175, 843 }, { 341, 148 }, { 423, 458 }, { 631, 659 }, { 428, 640 }, { 935, 649 }, { 946, 105 },
                { 950, 103 }, { 807, 964 }, { 976, 456 }, { 230, 607 }, { 460, 829 }, { 915, 489 }, { 164, 761 },
                { 815, 395 }, { 784, 385 }, { 417, 412 }, { 708, 124 }, { 247, 716 }, { 869, 695 }, { 179, 795 },
                { 504, 982 }, { 175, 497 }, { 215, 467 }, { 284, 638 }, { 638, 295 }, { 248, 510 }, { 481, 895 },
                { 715, 528 }, { 527, 549 }, { 478, 875 }, { 716, 764 }, { 327, 168 }, { 456, 526 }, { 170, 356 },
                { 249, 219 }, { 602, 850 }, { 142, 194 }, { 821, 609 }, { 104, 564 }, { 948, 842 }, { 851, 537 },
                { 375, 823 }, { 408, 792 }, { 195, 465 }, { 490, 241 }, { 981, 235 }, { 175, 265 }, { 679, 347 },
                { 293, 473 }, { 406, 259 }, { 156, 397 }, { 784, 360 }, { 306, 139 }, { 258, 689 }, { 369, 964 },
                { 386, 172 }, { 971, 475 }, { 893, 286 }, { 438, 842 }, { 690, 249 }, { 183, 643 }, { 374, 150 },
                { 256, 147 }, { 432, 346 }, { 508, 465 }, { 324, 546 }, { 584, 985 }, { 164, 160 }, { 395, 865 },
                { 381, 971 }, { 928, 742 }, { 862, 715 }, { 850, 296 }, { 491, 214 }, { 753, 650 }, { 814, 256 } };
        obstacles = new ArrayList<>();
        for (int[] block : blocks) {
            obstacles.add(arrayToList(block));
        }
        System.out.println(queensAttack(1000, 1000, 714, 169, obstacles));

    }

}