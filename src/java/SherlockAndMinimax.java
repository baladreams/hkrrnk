import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/sherlock-and-minimax/problem

public class SherlockAndMinimax {

    public static long sherlockAndMinimax(List<Long> arr, int p, int q) {
        long minP = Long.MAX_VALUE;
        long minQ = Long.MAX_VALUE;
        arr.sort(Comparator.naturalOrder());
        for (long a : arr) {
            if (a < p) {
                minP = Math.min(minP, Math.abs(a - p));
            } else if (a > p) {
                minQ = Math.min(minQ, Math.abs(a - q));
            }
        }
        arr = arr.stream().filter(x -> x >= p && x <= q).collect(Collectors.toList());
        minP = minP != Long.MAX_VALUE ? minP : 0;
        minQ = minQ != Long.MAX_VALUE ? minQ : 0;
        long minMax = minP;
        long miniMaxM = p;
        long startIndex = -1;
        for (long k : arr) {
            if (startIndex == -1) {
                if (arr.isEmpty() || minP > arr.get(0) - p) {
                    minMax = minP;
                    miniMaxM = p;
                } else {
                    long firstRangeStart = minP > 0 ? p - minP : p;
                    long firstRangeEnd = k;
                    long firstRange = firstRangeEnd - firstRangeStart;
                    long midPoint = firstRange / 2;
                    if (minP > 0)
                        miniMaxM = firstRangeStart + midPoint;
                    else
                        miniMaxM = p;
                    minMax = Math.max(miniMaxM - p, k - miniMaxM);
                }
            } else {
                long midPoint = (k - startIndex) / 2;
                if (minMax < midPoint) {
                    minMax = midPoint;
                    miniMaxM = startIndex + midPoint;
                }
            }
            startIndex = k;
        }
        if (startIndex == -1)
            startIndex = p;
        if (startIndex < q) {
            long lastRange = (q - startIndex) + minQ;
            long midPoint = lastRange / 2;
            if (minQ > midPoint) {
                if (minMax < minQ) {
                    miniMaxM = q;
                }
            } else {
                if (minMax < midPoint) {
                    miniMaxM = startIndex + midPoint;
                }
            }
        }
        return miniMaxM;
    }

    public static void main(String[] args) {
        System.out.println(sherlockAndMinimax(Arrays.asList(new Long[] { 3l, 5l, 7l, 9l }), 6, 8)); // 6
        System.out.println(sherlockAndMinimax(Arrays.asList(new Long[] { 5l, 8l, 14l }), 4, 9)); // 4
        System.out.println(sherlockAndMinimax(Arrays.asList(new Long[] { 38l, 50l, 60l, 30l, 48l }), 23, 69)); // 69
        System.out.println(sherlockAndMinimax(Arrays.asList(new Long[] { 3l, 24l, 35l, 6l, 7l, 45l }), 15, 20)); // 15
        System.out.println(sherlockAndMinimax(
                Arrays.asList(new Long[] { 7362l, 772l, 4354l, 5462l, 4924l, 5224l, 284l, 5996l, 2402l, 2540l, 6088l,
                        7764l, 4552l, 7230l, 6672l, 3444l, 2330l, 7616l, 7414l, 6738l, 3268l, 7268l, 7436l, 5812l,
                        6132l, 124l, 5068l, 7184l, 4922l, 3576l, 368l, 4224l, 58l, 7180l, 1014l, 1542l, 3018l, 1662l,
                        4160l, 1700l, 604l, 2666l, 4292l, 2416l, 5528l, 300l, 3746l, 6468l, 4404l, 626l }),
                2518, 4111)); // 3953
        System.out.println(sherlockAndMinimax(Arrays.asList(new Long[] { 263044060l, 323471968l, 60083128l, 764550014l,
                209332334l, 735326740l, 558683912l, 626871620l, 232673588l, 428805364l, 221674872l, 261029278l,
                139767646l, 146996700l, 200921412l, 121542678l, 96223500l, 239197414l, 407346706l, 143348970l,
                60722446l, 664904326l, 352123022l, 291011666l, 594294166l, 397870656l, 60694236l, 376586636l,
                486260888l, 114933906l, 493037208l, 5321608l, 90019990l, 601686988l, 712093982l, 575851770l, 411329684l,
                462785470l, 563110618l, 232790384l, 511246848l, 521904074l, 550301294l, 142371172l, 241067834l,
                14042944l, 249208926l, 36834004l, 69321106l, 467588012l, 92173320l, 360474676l, 221615472l, 340320496l,
                62541478l, 360772498l, 372355942l, 445408968l, 342087972l, 685617022l, 307398890l, 437939090l,
                720057720l, 718957462l, 387059594l, 583359512l, 589920332l, 500463226l, 770726204l, 434976772l,
                567860154l, 510626506l, 614077600l, 620953322l, 570332092l, 623026436l, 502427638l, 640333172l,
                370673998l }), 70283784, 302962359)); // 173959056
        System.out.println(sherlockAndMinimax(Arrays.asList(
                new Long[] { 7518l, 4798l, 5528l, 3806l, 7798l, 3396l, 6294l, 790l, 6724l, 3582l, 2336l, 4372l, 4746l,
                        7328l, 6822l, 1996l, 2004l, 5098l, 7376l, 7118l, 3478l, 2416l, 5310l, 3082l, 3288l, 2582l, 824l,
                        2832l, 4818l, 3508l, 1134l, 6640l, 5834l, 4068l, 3622l, 192l, 940l, 2564l, 5026l, 4708l, 4504l,
                        4828l, 2332l, 3948l, 5948l, 5676l, 2196l, 4206l, 7766l, 3710l, 4938l, 5688l, 3650l, 5824l,
                        4360l, 3786l, 6712l, 2856l, 5768l, 1826l, 2452l, 5874l, 964l, 1988l, 10l, 3226l, 2956l }),
                6449, 7347)); // 6467
        System.out.println(
                sherlockAndMinimax(Arrays.asList(new Long[] { 412812216l, 39588698l, 618476302l, 23949062l, 385062504l,
                        500380912l, 133993220l, 253538412l, 49853908l, 556943110l, 421175120l, 31001886l, 698632544l,
                        659644094l, 543241562l, 328693230l, 473767878l, 94650192l, 261298668l, 81020646l, 152674656l,
                        71260178l, 410072894l, 657224934l, 385308024l, 500873116l, 377377992l, 221445580l, 289020784l,
                        280110540l, 668245976l, 272868024l, 609082470l, 662923876l, 215496588l, 352866418l, 625495606l,
                        362490372l, 87327484l, 87297392l, 171418748l, 767049532l, 134155334l, 519780706l, 164644960l,
                        522353024l, 34967376l, 497247742l, 419476468l, 115591096l, 177450740l, 521051328l, 455428582l,
                        324065320l, 476256088l, 438088048l, 520947238l, 350333814l, 20101160l, 386338066l, 210167922l,
                        680539272l, 508259732l, 527823142l, 773567838l, 112882440l, 255369880l, 667356898l, 42151602l,
                        430074426l, 110779286l, 685509134l, 375584708l, 121084406l, 444677050l, 729861000l, 600868530l,
                        393698160l, 688872478l, 453800664l, 517424924l, 659752878l, 570023768l, 766153438l, 25728816l,
                        407515478l, 230783020l, 441631022l, 715996086l, 636198788l, 700192302l, 224742384l, 532679674l,
                        418665374l, 444987160l, 783318514l, 502872626l, 73389986l, 262999934l }), 2577535, 580702367)); // 2577535
    }
}
