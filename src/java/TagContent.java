import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// solution for https://www.hackerrank.com/challenges/tag-content-extractor/problem

public class TagContent {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] input = new String[] { "<h1>Nayeem loves counseling</h1>",
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
                "<Amee>safat codes like a ninja</amee>", "<SA premium>Imtiaz has a secret crush</SA premium>","qqoNVOmJDG@6IBDZoEmk9337LswEL&TQnLCuR`04XD%1t{G)Jmi_iNEXKwp&<iBKMbDGtF4v@coLsF1_LqgTJ3cSp& 3a~I&Q(j0h_w~Vk(oBZCL#vhYY9%c><wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>bMUGux)5n7L={M}e^`0xlSm5ce}ehiE}CJ6y0KPd~~B~ak5$PTdPGv}QnXpw6n9V8wVCVaTRTgLKkeF</wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>haZQKlWPxlRqXXkKHo=FDofc6$_S-GWA&m0zT*D~uorf_nAF^ym*U&6hGAI)s<XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><FbTSC#F104{py9Xl6s{yi-R~}k5Fv4i1kCgmBY7P=vVj-j48xUg8x9BCxl~Y><lyxRRMqnMBGj1_d7Qqh5Ebn7 aMb{Q0Dm){9~I0DTS8BZ7+bui~)rQ\"2Yb4f>EeZWvJvHIk</XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><BkkZV631Pnj}#%TWhZn@Y><kXjDpTvLA^tnXYb`h+cA J2","wRhDGQCG`r N4=cWqp4jF\"#I7#)jofKUYktmjH(\"s2nD4+NxsN)tpCf2U@78MsGNbEgSgR=6`\"y685~I(D-p&T2BnaJNa%S%y9pYMb_9v{PlScQ7R=~~xnSkpSd<orcsjzalN#eslhhH2d\"1MD)pzc*$tBG-mmI_*zW><xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)><ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2><DqcnlvJJa7ZYYrX^M*BI^*A_-mm0 HT4i^syNtujpEE8M@><mpX{FWXe#$d`)QdqT#eBbH=EJOXc@cFhxpJg14#*G0 o-w9G++`GA9U><PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%><WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#><gXzyXZFrpOGaC+M4)kG`d5L*lRW5A#O0i=V023f_iSwqL$_qRMp59PW gf6><GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>YjncQJp=mN8OV9WEviqvJT`YS$WD=^gryB)NWAal</GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>QMewBnIzsV</WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#></PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%>dPcdFnfTMuYyOFExhXoymEYRVptmHjbgR</ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2>wDegwAFAmjapXOejj</xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)>gEpaCbApodfJVPXVuct<bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C>rulVew89#uyWF}4`T\"xULOZ%1\"5Cu)&x7qD0</bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C><RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV><ofMiPADbv&XoS=rU{r5JRfE+R}DgSHV@%zDk1golMD_IMuJ1U`~~TyD><lCERjrGdOfuVC0\"8W##`L8x+qC=HW><UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_><pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY>nhPeJWSTIkb Gs_~ztKS*b#36E+ThD</pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY></UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_>zBesDtLSpVYfUzgds</lCERjrGdOfuVC0\"8W##`L8x+qC=HW>OjuAcMjbwMLuylplc</RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV>ZuWHgfxmI&l}D@r%q=mSj  Y&paOAZFf=~}I_Eg<CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS>RCKchSzpkdh0oF aNt (%Vu ^ap3K0GP634h_k%1FsO$Wu0n1aeLEPSBHpGpHdWrIrIApQrorxMIBVbyhh</CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS><XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb><RIEb0nii0bO5WV$%n#}TgB*fLl>RIAFwuGoRsU_9PleTRq{FS}fa3)1kRgn(C}ohOYXDqk</RIEb0nii0bO5WV$%n#}TgB*fLl>fQtRVddxdcuYJAikIUG</XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb>WmwMTg1FWlk0G6(mmh-SN\"k&hoKwzfF0PLel$2)aLS5tW22jz=zX9M7S88Hm_1aF7<rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1>tULUXNsa`iS~T(_BQ`EqJNSgxcO)$`~d=`F2Dqbst4-_N</rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1><MB=7g =8{_aUlwE_>MoUMPsYfBJNd$G~\"=dbO0U%aiOUaBBG&%(oFSfOZYvXSPFfwh@R QqyvTQ)3rJu%k6OBuBXkSShV=nm\"qJ46_VKi1- oHK^EdKTk~gs%t\"8<XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>LfOJpqbcGhsTzasaaYU</XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>YYYbEkrZ^wz=4046`&}N (^*_8EO^Eraw*jTFD~+IX%YX_Oo`f3BlFjmhSrEtE8FTe5870&+z(i@czXtI2mRfw7p^yFO_~r{*D0su6w&vnXb0aC+52Kol<byi)o%`Y{NBe>zYcZaQOKUCW4G0mJ$xe*xGIuY-&@1S`wkck%203&P{N}<lHrYNvRe#sQdvy-TurFDw+lUOyM1UI2sv`=><Nrp\"OjKiUXOhM(fRCqS\"C1-SmCTx%urq^=NEF-i*q&x><XKKZIu2zuIXam%aUp`bmJ(JwI`A", "<ICqG_u&B7vG-Sx=QZ+7dXnIUT_sqYHus{m%(2+(c%(XLr1&T@jGsIi><rCaKWG5#rf%@K`9}JMK\"L60-3$}Djx*EB><PybVItjSX725b*dXR0oq{ GP`><eDYLmHPfAX\"U#N1U=hyJP}mlgbeWuxXJq}DFDeJ``S><vpuji@fU~x6v&u`{-2RJMd6w(- z\"rZkB%w(J}3+{KjOMF7&aq#>VySkaxcRprzNDIRF~`Vy( &DdPIP_D0</rCaKWG5#rf%@K`9}JMK\"L60-3$}Djx*EB>Zjp<yclgxhVOdIC#7}r2MiBp>SWIKJGz`\"E=V&p2E0G&mQG=T08nFpbmiiveOfiLbO+2ylMvc&pLzeo#E2QDUwbGTvBMa</yclgxhVOdIC#7}r2MiBp><jOXHWajoyJX6F-GV9{(@ZN38gO63DJpa^1x+5@m&UZ#gW@\"VlAV-gifHc3>rRkDqq(LH$rMLNq$*HDpnDfJNP&\"7+&@Idm=xdm3SC2Imd$g</jOXHWajoyJX6F-GV9{(@ZN38gO63DJpa^1x+5@m&UZ#gW@\"VlAV-gifHc3><hkPHPaAfTf~-}L`(6+bw33cR8dGLEMIj>CEwBusjdY9zPR%)Ybm(BHfgbtVWlx^2_a0<EFMEApAfsGNrXG\"VJ~Y fS{sn\"\"GF`FGb G3~BmDcmA%9ih%kna\"=3 *sk\"b*c{Gq*><ZNcejfYtOhrJ9QVieIYqVLcCS~{`2B1UWAix$+U26 ~H>CwWEFVcUwnY_P$_UoG_DMbhFDMpuo@LQWk%4 i9Ol)Nan3{%xnDsGSgU0CAmLiEGUlmENXZlrAziHj</ZNcejfYtOhrJ9QVieIYqVLcCS~{`2B1UWAix$+U26 ~H></EFMEApAfsGNrXG\"VJ~Y fS{sn\"\"GF`FGb G3~BmDcmA%9ih%kna\"=3 *sk\"b*c{Gq*><wgoLPgz67he6*EX><YkkIKqjLmuG-5P1WIhoxqR4k(1BqKhW=WV#YQG5FvTH$w><VNXz1SzQI}JcCqzYl@f= Hg}1}Vb$arHOYR><mhdrn6EHW5`pPR~T8%@WpLEg-glBtI1*oVI~>UhxSlamxSMKnR(cnXLe#6S_*@)MJB qHs+\"v6hWkj0\"5 Mb0qy W</mhdrn6EHW5`pPR~T8%@WpLEg-glBtI1*oVI~></VNXz1SzQI}JcCqzYl@f= Hg}1}Vb$arHOYR>eoYV</wgoLPgz67he6*EX><vCQnOZQVI9pwbfB4*kFc+_Iw8%0Fl\"+RPNRI1VkiS5@4Kh-)O><YVUuRgY$aL_Y4bgtKH6ZN72IK+FCdLDxMn~Pz^EkHzT7uSc46ApA@></vCQnOZQVI9pwbfB4*kFc+_Iw8%0Fl\"+RPNRI1VkiS5@4Kh-)O><wDdU^7O9Q=5XRZ1HejD8%n b\"yOPm_R=4Q><kpWkNaJeueN4^Wf$duqow7jT-_e}jOnZHNX`_@S8# YGc1U3nmUL9{w><YMMVT5FPoO-sn7EuQhH>NxWRFRFdHc 5DtgC&8 CK9O=eGWtnSqnxL0OZb*Zy4MKTBKJW(^{r98 -+fbzL-g^ppcFdtXHedkVTdBTrVoH</YMMVT5FPoO-sn7EuQhH>haraGcjqNDGsqEGFmAMVecKHoZIIIFyhA<Fcs(bH`EIX8=slJ%b$XrLevRAxa^cCFbEVWJ@z)xH%Q)#A{~#^##J~><tVRsFQsPVp)sjruDI pJpP@a4Y3v1mT( t0$OKbgd@r5Kug8-(#X}f2*wo+tU6(#></tVRsFQsPVp)sjruDI pJpP@a4Y3v1mT( t0$OKbgd@r5Kug8-(#X}f2*wo+tU6(#>qlxvBNhSbKk&ICnY@*HLM}I 56vQAq-s%F{gE<nfFpoVFyV%YlUhjTCPirDSvF></nfFpoVFyV%YlUhjTCPirDSvF>NJheK6Km2cR$n_(*4zS0y4(+`KL=8~K1%\"FFfZf\"OZxR}MdI)N3_HyKhkjPYktpJmRRY~ECfqWR`u`cb`~(8@G9SMbvmE vdN8sjoEn~QpsvFNYUFeJDh^OFfe#lRHoyd#WlSDPGa-SWI&(9MT_Vc58BkmdFtHW~F5R=F^l%$%biS@yHQK=TRmkqNm }z9DAnOzGqJzw4p%y\"pXN_Yh&t7HqUuMU9qqj15A@Fs(gqxBbKvaT<bqqbbovwv**7 p$`6-toO_1F{z _ oNeD4QPDd5~T}&s2Qwu94vKlwAUvw 7ujybc><ithcUx~4O_\"%L\"4~=@ NI5Bto9Ft}-`@><rUtLhSg8}JCyctd3uZ gPPu$HL5kGJqM*&DFI><YEqgUxwp_8F1k&dS$f+O%r`%ml^a&Uz+JJsJ# lp(Zt}Cn></YEqgUxwp_8F1k&dS$f+O%r`%ml^a&Uz+JJsJ# lp(Zt}Cn>HswbySPOTMXzgRYD</bqqbbovwv**7 p$`6-toO_1F{z _ oNeD4QPDd5~T}&s2Qwu94vKlwAUvw 7ujybc>NUgslTB}GG%EO8pgG=97Cd{J5=%*#8*G@V*nkt9siuzZX&&<kaOpktvyp=e1%8%DYy`)z{QeiW#XYZd6tX=lG#9Pnwp`lZG5*Vv2D72><IgWrl~omG_8CCJj54V\"3\">QeHqQJtajefiCX=-UKx8q`-vf=gQ{264tn8=a</IgWrl~omG_8CCJj54V\"3\"></kaOpktvyp=e1%8%DYy`)z{QeiW#XYZd6tX=lG#9Pnwp`lZG5*Vv2D72>XUpAGM&ZQ9t3mGLdP*Uzs7=S%_4b9Pm(Gnx$QSdsMUqL2+waaK&4JuRyLk%9Wh+*<kdAqCLkc2h0WV_h_5KdR{Ukz6bVq+V&8S%3#_{mPE-Rlq$C-G28T*><IHWO-Pq+VcFLk1pbk8Zh^=a&+e9~><SNNeK}1dZh-v)5yPl3{eryk84-35)))D_sY`$bibjrG4wP+iOAGTx0{=7>mCUSWpzHaZD+dhLjAFsNj@ansSiRH$M6GH=KWhE~koYAmMeTDkZP9%%e_~CjPQ7OxaWqaroAbv</kdAqCLkc2h0WV_h_5KdR{Ukz6bVq+V&8S" }; 
                int testCases = input.length; // Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            String line = input[input.length - testCases];// in.nextLine();

            // Write your code here
            line = line.replaceAll("<", "</");
            String[] elements = line.split("[<|>]");
             String tagName = null; String data = null;
            List<String> dataList = new ArrayList<>();
            for (String e : elements) {
                if (e.startsWith("//")) {
                    if (e.length() > 2) {
                        String endTagName = e.substring(2);
                        if (endTagName.equals(tagName) && data != null)
                            dataList.add(data);
                    }
                    data = null;
                } else if (e.startsWith("/")) {
                    if (e.length() > 1) {
                        tagName = e.substring(1);
                    }
                    data = null;
                } else {
                    if (e.length() > 0)
                        data = e;
                }
            }
            if (dataList.isEmpty())
                System.out.println("None");
            else
                for (String e : dataList)
                    System.out.println(e);

            testCases--;
        }
    }
}
