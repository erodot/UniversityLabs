using System.Collections.Generic;
using System.Linq;
using System;

namespace DecisionTheory
{
    public class Kemeny{
        public static Matrix<int> DistanceAll(ClasteredRanking[] clasteredRankings){
            uint rank = (uint)clasteredRankings.Length;
            List<int> matrix = new List<int>();
            foreach(ClasteredRanking first in clasteredRankings)
                foreach(ClasteredRanking second in clasteredRankings)
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

        public static ClasteredRanking Median(ClasteredRanking[] clasteredRankings){
            Matrix<int> distances = DistanceAll(clasteredRankings);
            Vector<int> rowSum = distances.GetRowsSum();
            int min_i = 0; // first ranking
            for(int i = 0; i< rowSum.length; i++)
                if(rowSum.get(i) < rowSum.get(min_i))
                    min_i = i;

            return clasteredRankings[min_i];
        }
    }
}