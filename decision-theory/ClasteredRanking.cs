using System;
using System.Collections.Generic;
using System.Linq;

namespace DecisionTheory{
    public class ClasteredRanking{
        private Vector<int> ranking;

        private List<Tuple<int, List<int>>> ranks { get {
            List<Tuple<int, List<int>>> ranks = new List<Tuple<int, List<int>>>();
            for(int i = 1; i <= ranking.ToList().Max(); i++)
                ranks.Add(new Tuple<int, List<int>>(i, new List<int>()));
            
            for(int i=0; i<ranking.length; i++){
                ranks.First(t => t.Item1 == ranking.get(i)).Item2.Add(i);
            }
            return ranks;
        }}

        public ClasteredRanking(Vector<int> withRanking){
            this.ranking = withRanking;
        }

        public void Print(string withHeader = "", bool withLetters = false){
            List<string> clusters = new List<string>();
            
            foreach(Tuple<int, List<int>> rank in ranks){
                if(rank.Item2.Count == 0)
                    continue;

                if(rank.Item2.Count == 1){
                    clusters.Add(GetVariableName(rank.Item2[0], withLetters));
                }
                else{
                    IEnumerable<string> numbers = rank.Item2.Select(r => GetVariableName(r, withLetters));
                    string cluster = "{ " + string.Join(", ", numbers) + " }";
                    clusters.Add(cluster);
                }
            }
            string clustersString = string.Join(" < ", clusters);
            if(!string.IsNullOrEmpty(withHeader))
                Console.Write(withHeader + " ");
            Console.WriteLine(clustersString);
        }

        private static string GetVariableName(int i, bool withLetters){
            return withLetters 
                    ? ((char)(((int)'А') + i)).ToString()
                    : (i + 1).ToString() ;
        }

        public static void PrintContradictionCore(ClasteredRanking first, ClasteredRanking second, string withHeader = "", bool withLetters = false){
            List<Tuple<int,int>> contradictions = ClasteredRanking.GetContradictionCore(first, second);
            List<string> contradictionElems = contradictions.Select(x => GetVariableName(x.Item1, withLetters) + ", " + GetVariableName(x.Item2, withLetters)).ToList();
            string contradictionString = "[(" + string.Join("), (", contradictionElems) + ")]";
            
            if(!string.IsNullOrEmpty(withHeader))
                contradictionString = withHeader + " " + contradictionString;

            Console.WriteLine(contradictionString);
        }

        private static List<Tuple<int, int>> GetContradictionCore(ClasteredRanking first, ClasteredRanking second){
            if(first.ranking.length != second.ranking.length)
                throw new Exception("Clastered rankings have different number of elements.");

            int rankingLength = (int)first.ranking.length;
            List<Tuple<int,int>> contradictions = new List<Tuple<int, int>>();

            for(int i=0; i<rankingLength; i++)
                for(int j=i+1; j<rankingLength; j++){
                    bool firstContradiction = first.ranking.get(i) < first.ranking.get(j) && second.ranking.get(i) > second.ranking.get(j);
                    bool secondContradiction = first.ranking.get(i) > first.ranking.get(j) && second.ranking.get(i) < second.ranking.get(j);
                    if(firstContradiction || secondContradiction)
                        contradictions.Add(new Tuple<int,int>(i, j));
                }
            return contradictions;  
        }
    }
}