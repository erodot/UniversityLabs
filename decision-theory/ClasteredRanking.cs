using System;
using System.Collections.Generic;
using System.Linq;

namespace DecisionTheory{
    public class ClasteredRanking{
        public Ranking ranking { get; }
        
        public ClasteredRanking(Vector<int> withVectorRanking){
            this.ranking = new Ranking(){ vectorRanking = withVectorRanking };
        }

        public ClasteredRanking(List<List<int>> withClusteredRanking){
            this.ranking = new Ranking(){ clusteredRanking = withClusteredRanking };
        }

        public void Print(string withHeader = "", bool withLetters = false){
            List<string> clusters = new List<string>();
            
            foreach(Tuple<int, List<int>> rank in ranking.tupleRanking){
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
            if(first.ranking.vectorRanking.length != second.ranking.vectorRanking.length)
                throw new Exception("Clastered rankings have different number of elements.");

            int rankingLength = (int)first.ranking.vectorRanking.length;
            List<Tuple<int,int>> contradictions = new List<Tuple<int, int>>();

            for(int i=0; i<rankingLength; i++)
                for(int j=i+1; j<rankingLength; j++){
                    bool firstContradiction = first.ranking.vectorRanking.get(i) < first.ranking.vectorRanking.get(j) && second.ranking.vectorRanking.get(i) > second.ranking.vectorRanking.get(j);
                    bool secondContradiction = first.ranking.vectorRanking.get(i) > first.ranking.vectorRanking.get(j) && second.ranking.vectorRanking.get(i) < second.ranking.vectorRanking.get(j);
                    if(firstContradiction || secondContradiction)
                        contradictions.Add(new Tuple<int,int>(i, j));
                }
            return contradictions;  
        }

        public static ClasteredRanking GetReconcilicationRanking(ClasteredRanking first, ClasteredRanking second){
            // hardcoded under certain input data
            return new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){4},new List<int>(){2, 3, 6}, new List<int>(){1}, new List<int>(){7}, new List<int>(){5, 8},
                });
        }
    }
}