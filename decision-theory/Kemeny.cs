using System.Collections.Generic;
using System.Linq;
using System;

namespace DecisionTheory
{
    public class Kemeny{
        public static Matrix<int> DistanceAll(ClasteredRanking[] rankings){
            uint rank = (uint)rankings.Length;
            List<int> matrix = new List<int>();
            foreach(ClasteredRanking first in rankings)
                foreach(ClasteredRanking second in rankings)
                    matrix.Add(Distance(first, second));
            return new Matrix<int>(rank, rank, matrix.ToArray());
        }

        public static int Distance(ClasteredRanking first, ClasteredRanking second){
            Vector<int> firstVR = first.ranking.vectorRanking;
            Vector<int> secondVR = second.ranking.vectorRanking;

            if(firstVR.length != secondVR.length)
                throw new Exception("Rankings has different number of options.");

            int distance = 0;
            for(int i=0; i<firstVR.length; i++)
                distance += Math.Abs(firstVR.get(i) - secondVR.get(i));

            return distance;
        }

        public static int Median(ClasteredRanking[] clasteredRankings){
            throw new NotImplementedException();
        }
    }
}