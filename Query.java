
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Query {
	public static void main(String[] args) throws IOException {
		
		//read query document
		BufferedReader queryIn;
		queryIn = new BufferedReader(
				new FileReader(
						"E:/Winter 2014/Information Retrieval and the Internet/Assignment1/topics_MB1-49.txt"));
		
		//read tweet document
		BufferedReader docIn;
		docIn = new BufferedReader(new FileReader(
				"D:/EclipseWorkspace/hashTable/src/regExp/t.txt"));
		
		//set location of output txt file
		File f = new File("d:\\ss.txt");
		FileOutputStream output = null;
		output = new FileOutputStream(f, true);

		//define a decimal format to round off double variables
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
		
		String s;	//String s is used for saving contents of each query
		String doc;	//doc is used for saving contents of each tweet
		int queryId = 1;	//query counter variable
		
		//read every line of query file by repetition
		while ((s = queryIn.readLine()) != null) {
			//if this line contains <title> element
			if (s.indexOf("<title>") != -1) {
				List<String> query = new ArrayList<String>();
				String queryTitle = "";
				String[] split1 = Pattern.compile("<title>|</title>").split(s,
						3); // fetch contents between <title> and </title>, and put them into queryTitle variable
				queryTitle = split1[1];

				//split split1 variable into every single word, put them into spilt2 array
				String[] split2 = Pattern.compile("[^A-Za-z0-9_]").split(
						queryTitle);

				//put data of string array into query arraylist by repetition
				for (int i = 0; i < split2.length; i++) {
					if (!split2[i].equals("")) {
						query.add(split2[i].toLowerCase());
					}
				}

				System.out.println(query);	//print queries on the console
				
				//docCos two-dimension array saves tweetId and Cos
				double docCos[][] = new double[2][46067];
				
				//counter variables
				int docCounter = 0;
				int relDocCounter = 0;
				
				//read tweet file by repetition
				while ((doc = docIn.readLine()) != null) {
					String[] split3 = Pattern.compile("\t").split(doc, 2); //split content from tweet Id 

					String tweetId = split3[0];
					String tweetText = split3[1];

					// transfer split4 into list type
					String[] split4 = Pattern.compile("[^A-Za-z0-9_]").split(
							tweetText);
					List<String> list = new ArrayList<String>();	//The list variable is used for saving string arraylist which has been splitted
					
					// 循环把字符串数组的数据存到list arraylist 里
					for (int i = 0; i < split4.length; i++) {
						if (!split4[i].equals("")) {
							list.add(split4[i].toLowerCase());
						}

					}

					// delete redundant words
					for (int i = 0, len = list.size(); i < len; ++i) {

						Pattern pattern = Pattern
								.compile("|a|about|above|ac|according|accordingly|across|actually|ad|adj|af|after|afterwards|again|against|al|albeit|all|almost|alone|along|already|als|also|although|always|am|among|amongst|an|and|another|any|anybody|anyhow|anyone|anything|anyway|anywhere|apart|apparently|are|aren|arise|around|as|aside|at|au|auf|aus|aux|av|avec|away|b|be|became|because|become|becomes|becoming|been|before|beforehand|began|begin|beginning|begins|behind|bei|being|below|beside|besides|best|better|between|beyond|billion|both|briefly|but|by|c|came|can|cannot|canst|caption|captions|certain|certainly|cf|choose|chooses|choosing|chose|chosen|clear|clearly|co|come|comes|con|contrariwise|cos|could|couldn|cu|d|da|dans|das|day|de|degli|dei|del|della|delle|dem|den|der|deren|des|di|did|didn|die|different|din|do|does|doesn|doing|don|done|dos|dost|double|down|du|dual|due|durch|during|e|each|ed|eg|eight|eighty|either|el|else|elsewhere|em|en|end|ended|ending|ends|enough|es|especially|et|etc|even|ever|every|everybody|everyone|everything|everywhere|except|excepts|excepted|excepting|exception|exclude|excluded|excludes|excluding|exclusive|f|fact|facts|far|farther|farthest|few|ff|fifty|finally|first|five|foer|follow|followed|follows|following|for|former|formerly|forth|forty|forward|found|four|fra|frequently|from|front|fuer|further|furthermore|furthest|g|gave|general|generally|get|gets|getting|give|given|gives|giving|go|going|gone|good|got|great|greater|h|had|haedly|half|halves|hardly|has|hasn|hast|hath|have|haven|having|he|hence|henceforth|her|here|hereabouts|hereafter|hereby|herein|hereto|hereupon|hers|herself|het|high|higher|highest|him|himself|hindmost|his|hither|how|however|howsoever|hundred|hundreds|i|ie|if|ihre|ii|im|immediately|important|in|inasmuch|inc|include|included|includes|including|indeed|indoors|inside|insomuch|instead|into|inward|is|isn|it|its|itself|j|ja|journal|journals|just|k|kai|keep|keeping|kept|kg|kind|kinds|km|l|la|large|largely|larger|largest|las|last|later|latter|latterly|le|least|les|less|lest|let|like|likely|little|ll|long|longer|los|low|lower|lowest|ltd|m|made|mainly|make|makes|making|many|may|maybe|me|meantime|meanwhile|med|might|million|mine|miss|mit|more|moreover|most|mostly|mr|mrs|ms|much|mug|must|my|myself|n|na|nach|namely|nas|near|nearly|necessarily|necessary|need|needs|needed|needing|neither|nel|nella|never|nevertheless|new|next|nine|ninety|no|nobody|none|nonetheless|noone|nope|nor|nos|not|note|noted|notes|noting|nothing|notwithstanding|now|nowadays|nowhere|o|obtain|obtained|obtaining|obtains|och|of|off|often|og|ohne|ok|old|om|on|once|onceone|one|only|onto|or|ot|other|others|otherwise|ou|ought|our|ours|ourselves|out|outside|over|overall|owing|own|p|par|para|particular|particularly|past|per|perhaps|please|plenty|plus|por|possible|possibly|pour|poured|pouring|pours|predominantly|previously|pro|probably|prompt|promptly|provide|provides|provided|providing|q|quite|r|rather|re|ready|really|recent|recently|regardless|relatively|respectively|round|s|said|same|sang|save|saw|say|second|see|seeing|seem|seemed|seeming|seems|seen|sees|seldom|self|selves|send|sending|sends|sent|ses|seven|seventy|several|shall|shalt|she|short|should|shouldn|show|showed|showing|shown|shows|si|sideways|significant|similar|similarly|simple|simply|since|sing|single|six|sixty|sleep|sleeping|sleeps|slept|slew|slightly|small|smote|so|sobre|some|somebody|somehow|someone|something|sometime|sometimes|somewhat|somewhere|soon|spake|spat|speek|speeks|spit|spits|spitting|spoke|spoken|sprang|sprung|staves|still|stop|strongly|substantially|successfully|such|sui|sulla|sung|supposing|sur|t|take|taken|takes|taking|te|ten|tes|than|that|the|thee|their|theirs|them|themselves|then|thence|thenceforth|there|thereabout|thereabouts|thereafter|thereby|therefor|therefore|therein|thereof|thereon|thereto|thereupon|these|they|thing|things|third|thirty|this|those|thou|though|thousand|thousands|three|thrice|through|throughout|thru|thus|thy|thyself|til|till|time|times|tis|to|together|too|tot|tou|toward|towards|trillion|trillions|twenty|two|u|ueber|ugh|uit|un|unable|und|under|underneath|unless|unlike|unlikely|until|up|upon|upward|us|use|used|useful|usefully|user|users|uses|using|usually|v|van|various|ve|very|via|vom|von|voor|vs|w|want|was|wasn|way|ways|we|week|weeks|well|went|were|weren|what|whatever|whatsoever|when|whence|whenever|whensoever|where|whereabouts|whereafter|whereas|whereat|whereby|wherefore|wherefrom|wherein|whereinto|whereof|whereon|wheresoever|whereto|whereunto|whereupon|wherever|wherewith|whether|whew|which|whichever|whichsoever|while|whilst|whither|who|whoever|whole|whom|whomever|whomsoever|whose|whosoever|why|wide|widely|will|wilt|with|within|without|won|worse|worst|would|wouldn|wow|x|xauthor|xcal|xnote|xother|xsubj|y|ye|year|yes|yet|yipee|you|your|yours|yourself|yourselves|yu|z|za|ze|zu|zum|1|2|3|4|5|6|7|8|9|0");
						Matcher matcher = pattern.matcher(list.get(i));

						if (matcher.matches() || list.get(i) == "") {
							list.remove(i);
							len--;
						}

					}
					

					// calculate query's weight
					int[] wQuery = new int[query.size()];
					for (int i = 0; i < query.size(); i++) {
						wQuery[i] = 1;
					}

					// calculate tweet's weight
					int[] wDoc = new int[query.size()];
					for (int i = 0; i < query.size(); i++) {
						for (int j = 0; j < list.size(); j++) {
							if (list.get(j).equals(query.get(i))) {
								wDoc[i]++;
							}
						}
					}

					// calculate cos
					int up = 0;
					int querySquSum = 0;
					int docSquSum = 0;
					double cos = 0;
					for (int i = 0; i < wQuery.length; i++) {
						up = up + wQuery[i] * wDoc[i];
						querySquSum = querySquSum + wQuery[i] * wQuery[i];
						docSquSum = docSquSum + wDoc[i] * wDoc[i];
					}
					
					//denominator is 0?
					if (!(docSquSum == 0)) {
						cos = up
								/ (Math.sqrt(querySquSum) * Math
										.sqrt(docSquSum));
						docCos[0][relDocCounter] = Double.valueOf(tweetId);
						docCos[1][relDocCounter] = cos;
						relDocCounter++;
					} else {
						cos = 0;
					}

					docCounter++;
					if (docCounter == 46067) {
						break;
					}

				}
				// reading tweet completed

				// sort docCos according to their cos
				for (int i = 0; i < relDocCounter - 1; i++) {
					for (int j = i + 1; j < relDocCounter; j++) {
						if (docCos[1][i] < docCos[1][j]) {
							double num = docCos[0][i];
							double cos = docCos[1][i];
							docCos[0][i] = docCos[0][j];
							docCos[1][i] = docCos[1][j];
							docCos[0][j] = num;
							docCos[1][j] = cos;
						}
					}
				}
				
				//print result into txt file
				for (int i = 0; i < relDocCounter; i++) {
					if (!(docCos[1][i] == 0)) {
						System.out.println("TweetID:" + (long) docCos[0][i]
								+ "    Relevance:" + df.format(docCos[1][i]));

						try {
							output = new FileOutputStream(f, true);
							String text = "QueryID:"+queryId+"  TweetID:" + (long) docCos[0][i]
									+ "    Relevance:" + df.format(docCos[1][i]) + "\r\n";
							output.write(text.getBytes());
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							try {
								output.close();
							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
						}

					} else {
						break;
					}

				}

				// back to top of the tweet document
				docIn = new BufferedReader(new FileReader(
						"D:/EclipseWorkspace/hashTable/src/regExp/t.txt"));

				queryId++;
			}

		}

		queryIn.close();
		docIn.close();
	}
}
