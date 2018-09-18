
package demo;

import java.util.ArrayList;
import util.Question;

/**
 *
 * @author Mirko
 */
public class DemoTestData {
    
    public static boolean showHints = false;
    
    public static String[] questions = new String[95];
    public static ArrayList<Question> questionList;
    static {
            questionList = new ArrayList<Question>();
            
            String answer1 = "";
            String answer2 = "";
            String answer3 = "";
            String answer4 = "";
            
            questions[0] = "What is message authentication?";
            answer1 = "A service used to verify the" + "\n" + 
                      "integrity of the message";
            answer2 = "A service used to encrypt" + "\n" + 
                      "the message";
            answer3 = "A service used to automatically" + "\n" + 
                      "compress the message";
            answer4 = "A service used to automatically" + "\n" + 
                      "convert the message";
            Question q0 = new Question(questions[0], answer1, answer2, answer3, answer4, 1);
            questionList.add(q0);
            
            questions[1] = "What does message authentication assure?";
            answer1 = "That data received are exactly" + "\n" + 
                      "as sent";
            answer2 = "That data has reached the" + "\n" + 
                      "destination";
            answer3 = "That data has been sent without" + "\n" + 
                      "problems";
            answer4 = "That the recipient hes seen the" + "\n" + 
                      "message";
            Question q1 = new Question(questions[1], answer1, answer2, answer3, answer4, 1);
            questionList.add(q1);
            
            questions[2] = "What does message authentication assure?";
            answer1 = "That the identity of the sender" + "\n" + 
                      "is valid";
            answer2 = "That data has reached the" + "\n" + 
                      "destination";
            answer3 = "That data has been sent without" + "\n" + 
                      "problems";
            answer4 = "That the recipient hes seen the" + "\n" + 
                      "message";
            Question q2 = new Question(questions[2], answer1, answer2, answer3, answer4, 1);
            questionList.add(q2);
            
            questions[3] = "Which of these is NOT an algorithm used for generating" + "\n" + 
                           "the hash code?";
            answer1 = "CAST5 (CAST-128)";
            answer2 = "MD5";
            answer3 = "SHA-1";
            answer4 = "SHA-224";
            Question q3 = new Question(questions[3], answer1, answer2, answer3, answer4, 1);
            questionList.add(q3);
            
            questions[4] = "Which of these is NOT an algorithm used for generating" + "\n" + 
                           "the hash code?";
            answer1 = "IDEA";
            answer2 = "MD5";
            answer3 = "SHA-256";
            answer4 = "SHA-224";
            Question q4 = new Question(questions[4], answer1, answer2, answer3, answer4, 1);
            questionList.add(q4);
            
            questions[5] = "Which of these is NOT an algorithm used for generating" + "\n" + 
                           "the hash code?";
            answer1 = "Triple DES";
            answer2 = "MD5";
            answer3 = "SHA-1";
            answer4 = "SHA-224";
            Question q5 = new Question(questions[5], answer1, answer2, answer3, answer4, 1);
            questionList.add(q5);
            
            questions[6] = "Which of these is algorithms is used for generating" + "\n" + 
                           "the hash code of the message?";
            answer1 = "SHA-1";
            answer2 = "CAST5";
            answer3 = "IDEA";
            answer4 = "Triple DES";
            Question q6 = new Question(questions[6], answer1, answer2, answer3, answer4, 1);
            questionList.add(q6);
            
            questions[7] = "Which of these is algorithms is used for generating" + "\n" + 
                           "the hash code of the message?";
            answer1 = "MD5";
            answer2 = "CAST5";
            answer3 = "IDEA";
            answer4 = "El Gamal";
            Question q7 = new Question(questions[7], answer1, answer2, answer3, answer4, 1);
            questionList.add(q7);
            
            questions[8] = "Which of these is algorithms is used for generating" + "\n" + 
                           "the hash code of the message?";
            answer1 = "SHA-256";
            answer2 = "RSA";
            answer3 = "IDEA";
            answer4 = "CAST5";
            Question q8 = new Question(questions[8], answer1, answer2, answer3, answer4, 1);
            questionList.add(q8);
            
            questions[9] = "What is a hash code?";
            answer1 = "A fix-length value that depends" + "\n" + 
                      "on the message";
            answer2 = "A fix-length value that depends" + "\n" + 
                      "on the sender";
            answer3 = "A secret value that users share";
            answer4 = "A variable-length value that" + "\n" + 
                      "depends on the message";
            Question q9 = new Question(questions[9], answer1, answer2, answer3, answer4, 1);
            questionList.add(q9);
            
            questions[10] = "When signing the message, hash code is encrypted with:";
            answer1 = "Sender's private key";
            answer2 = "Sender's public key";
            answer3 = "Recipient's session key";
            answer4 = "Recipient's public key";
            Question q10 = new Question(questions[10], answer1, answer2, answer3, answer4, 1);
            questionList.add(q10);
            
            questions[11] = "When signing the message, hash code is encrypted with:";
            answer1 = "Sender's private key";
            answer2 = "Sender's public key";
            answer3 = "Sender's session key";
            answer4 = "Shared secret key";
            Question q11 = new Question(questions[11], answer1, answer2, answer3, answer4, 1);
            questionList.add(q11);
            
            questions[12] = "Which of these is algorithms can be used for encryption" + "\n" + 
                            "of the hash code?";
            answer1 = "RSA";
            answer2 = "SHA-1";
            answer3 = "MD5";
            answer4 = "CAST5";
            Question q12 = new Question(questions[12], answer1, answer2, answer3, answer4, 1);
            questionList.add(q12);
            
            questions[13] = "Which of these is algorithms can be used for encryption" + "\n" + 
                            "of the hash code?";
            answer1 = "DSA";
            answer2 = "SHA-256";
            answer3 = "El Gamal";
            answer4 = "CAST5";
            Question q13 = new Question(questions[13], answer1, answer2, answer3, answer4, 1);
            questionList.add(q13);
            
            questions[14] = "What does Timestamp field in the signature component mean?";
            answer1 = "The time of signature creation";
            answer2 = "The time of message creation";
            answer3 = "The time when signature" + "\n" + 
                      "expires";
            answer4 = "The time when signature" + "\n" + 
                      "becomes valid";
            Question q14 = new Question(questions[14], answer1, answer2, answer3, answer4, 1);
            questionList.add(q14);
            
            questions[15] = "What does Message Digest field in the signature component contain?";
            answer1 = "Encrypted hash code";
            answer2 = "Encrypted message";
            answer3 = "Compressed message";
            answer4 = "Hash code of the compressed" + "\n" + 
                      "message";
            Question q15 = new Question(questions[15], answer1, answer2, answer3, answer4, 1);
            questionList.add(q15);
            
            questions[16] = "When authenticating the message, recipient decrypts message" + "\n" +
                            "digest with:";
            answer1 = "Sender's public key";
            answer2 = "Sender's private key";
            answer3 = "Sender's session key";
            answer4 = "His own private key";
            Question q16 = new Question(questions[16], answer1, answer2, answer3, answer4, 1);
            questionList.add(q16);
            
            questions[17] = "When authenticating the message, recipient decrypts message" + "\n" +
                            "digest with:";
            answer1 = "Sender's public key";
            answer2 = "His own private key";
            answer3 = "His own public key";
            answer4 = "His own session key";
            Question q17 = new Question(questions[17], answer1, answer2, answer3, answer4, 1);
            questionList.add(q17);
            
            questions[18] = "What does the recipient compare when authenticating the message?";
            answer1 = "Generated hash code and" + "\n" +
                      "decrypted message digest";
            answer2 = "Decrypted message and" + "\n" + 
                      "generated hash code";
            answer3 = "Decrypted message and" + "\n" + 
                      "decrypted message digest";
            answer4 = "Generated hash code and" + "\n" + 
                      "generated message digest";
            Question q18 = new Question(questions[18], answer1, answer2, answer3, answer4, 1);
            questionList.add(q18);
            
            questions[19] = "Confidentiality is provided by:";
            answer1 = "Encrypting message";
            answer2 = "Signing message";
            answer3 = "Encrypting sender's identity";
            answer4 = "Decrypting message";
            Question q19 = new Question(questions[19], answer1, answer2, answer3, answer4, 2);
            questionList.add(q19);
            
            questions[20] = "What does confidentiality assure?";
            answer1 = "That the message can be read" + "\n" + 
                      "only by the recipient";
            answer2 = "That the message can be read" + "\n" + 
                      "only by the sender";
            answer3 = "That the message has been safely" + "\n" + 
                      "delivered";
            answer4 = "That the sender is authentic";
            Question q20 = new Question(questions[20], answer1, answer2, answer3, answer4, 2);
            questionList.add(q20);
            
            questions[21] = "What is used as session key when encrypting the message?";
            answer1 = "Hash code of the passphrase";
            answer2 = "Hash code of the sender's" + "\n" + 
                      "public key";
            answer3 = "Sender's public key";
            answer4 = "Sender's private key";
            Question q21 = new Question(questions[21], answer1, answer2, answer3, answer4, 2);
            questionList.add(q21);
            
            questions[22] = "How many times can session key be used?";
            answer1 = "Only once";
            answer2 = "Two times";
            answer3 = "As long as the session is" + "\n" + 
                      "valid";
            answer4 = "Unlimited number of times";
            Question q22 = new Question(questions[22], answer1, answer2, answer3, answer4, 2);
            questionList.add(q22);
            
            questions[23] = "What can be used instead of passphrase when creating session key?";
            answer1 = "Message itself";
            answer2 = "Sender's public key";
            answer3 = "Encrypted user ID";
            answer4 = "Hash code of the signature";
            Question q23 = new Question(questions[23], answer1, answer2, answer3, answer4, 2);
            questionList.add(q23);
            
            questions[24] = "Which of these algorithms is used when encrypting the message?";
            answer1 = "CAST5";
            answer2 = "SAH-1";
            answer3 = "MD5";
            answer4 = "RSA";
            Question q24 = new Question(questions[24], answer1, answer2, answer3, answer4, 2);
            questionList.add(q24);
            
            questions[25] = "Which of these algorithms is used when encrypting the message?";
            answer1 = "IDEA";
            answer2 = "El Gamal";
            answer3 = "DSA";
            answer4 = "RSA";
            Question q25 = new Question(questions[25], answer1, answer2, answer3, answer4, 2);
            questionList.add(q25);
            
            questions[26] = "Which of these algorithms is used when encrypting the message?";
            answer1 = "Triple DES";
            answer2 = "CAST5";
            answer3 = "MD5";
            answer4 = "RSA";
            Question q26 = new Question(questions[26], answer1, answer2, answer3, answer4, 2);
            questionList.add(q26);
            
            questions[27] = "When confidentiality service is used, session key is sent:";
            answer1 = "Encrypted with recipient's" + "\n" + 
                      "public key";
            answer2 = "Encrypted with recipient's" + "\n" + 
                      "private key";
            answer3 = "Encrypted with sender's" + "\n" + 
                      "public key";
            answer4 = "Encrypted with sender's" + "\n" +
                      "private key";
            Question q27 = new Question(questions[27], answer1, answer2, answer3, answer4, 2);
            questionList.add(q27);
            
            questions[28] = "When confidentiality service is used, session key can be" + "\n" + 
                            "encrypted using:";
            answer1 = "RSA";
            answer2 = "SHA-1";
            answer3 = "IDEA";
            answer4 = "Triple DES";
            Question q28 = new Question(questions[28], answer1, answer2, answer3, answer4, 2);
            questionList.add(q28);
            
            questions[29] = "When confidentiality service is used, session key can be" + "\n" + 
                            "encrypted using which algorithm:";
            answer1 = "El Gamal";
            answer2 = "SHA-1";
            answer3 = "IDEA";
            answer4 = "Triple DES";
            Question q29 = new Question(questions[29], answer1, answer2, answer3, answer4, 2);
            questionList.add(q29);
            
            questions[30] = "What does recipient use to recover the session key?";
            answer1 = "His private key";
            answer2 = "His public key";
            answer3 = "Sender's public key";
            answer4 = "Passphrase";
            Question q30 = new Question(questions[30], answer1, answer2, answer3, answer4, 2);
            questionList.add(q30);
            
            questions[31] = "What does recipient use to recover the session key?";
            answer1 = "His private key";
            answer2 = "Sender's private key";
            answer3 = "Sender's public key";
            answer4 = "His public key";
            Question q31 = new Question(questions[31], answer1, answer2, answer3, answer4, 2);
            questionList.add(q31);
            
            questions[32] = "What does recipient use to decrypt the message?";
            answer1 = "Session key";
            answer2 = "His private key";
            answer3 = "Sender's private key";
            answer4 = "His public key";
            Question q32 = new Question(questions[32], answer1, answer2, answer3, answer4, 2);
            questionList.add(q32);
            
            questions[33] = "What does recipient use to decrypt the message?";
            answer1 = "Session key";
            answer2 = "Message Digest";
            answer3 = "Sender's public key";
            answer4 = "Sender's private key";
            Question q33 = new Question(questions[33], answer1, answer2, answer3, answer4, 2);
            questionList.add(q33);
            
            questions[34] = "Radix64 converts:";
            answer1 = "A group of 3 octets into 4" + "\n" + 
                      "printable ASCII characters";
            answer2 = "A group of 4 octets into 3" + "\n" + 
                      "printable ASCII characters";
            answer3 = "A group of 3 ASCII characters" + "\n" + 
                      "into 4 octets";
            answer4 = "A group of 3 octets into 4" + "\n" + 
                      "octets";
            Question q34 = new Question(questions[34], answer1, answer2, answer3, answer4, 3);
            questionList.add(q34);
            
            questions[35] = "If the input 0x14FB9C03D9FE is converted to Radix64" + "\n" +
                            "what is the output?";
            answer1 = "FpucA9l+";
            answer2 = "FA9l+uc=";
            answer3 = "FpucAl==";
            answer4 = "FucpAl9F";
            Question q35 = new Question(questions[35], answer1, answer2, answer3, answer4, 3);
            questionList.add(q35);
            
            questions[36] = "If the input 0x14FB9C03D9 is converted to Radix64" + "\n" +
                            "what is the output?";
            answer1 = "FpucA9k=";
            answer2 = "FpucA9==";
            answer3 = "Fpuc==A9";
            answer4 = "FucpA9kl";
            Question q36 = new Question(questions[36], answer1, answer2, answer3, answer4, 3);
            questionList.add(q36);
            
            questions[37] = "If the input 0x14FB9C03 is converted to Radix64" + "\n" +
                            "what is the output?";
            answer1 = "FpucAw==";
            answer2 = "FpucwAwA";
            answer3 = "FupcwA9=";
            answer4 = "FpcuAw9k=";
            Question q37 = new Question(questions[37], answer1, answer2, answer3, answer4, 3);
            questionList.add(q37);
            
            questions[38] = "When converting to Radix64, group of 4 6-bit" + "\n" +
                            "(decimal) 0 0 0 0 is converted to:";
            answer1 = "AAAA";
            answer2 = "0000";
            answer3 = "aaaa";
            answer4 = "9999";
            Question q38 = new Question(questions[38], answer1, answer2, answer3, answer4, 3);
            questionList.add(q38);
            
            questions[39] = "When converting to Radix64, group of 4 6-bit" + "\n" +
                            "(decimal) 3 15 6 62 is converted to:";
            answer1 = "DPG+";
            answer2 = "dpg-";
            answer3 = "30+F";
            answer4 = "DpG=";
            Question q39 = new Question(questions[39], answer1, answer2, answer3, answer4, 3);
            questionList.add(q39);
            
            questions[40] = "When converting to Radix64, group of 3 6-bit" + "\n" +
                            "(decimal) 3 15 6 is converted to:";
            answer1 = "DPG=";
            answer2 = "DPGA";
            answer3 = "DGAP";
            answer4 = "GDPA";
            Question q40 = new Question(questions[40], answer1, answer2, answer3, answer4, 3);
            questionList.add(q40);
            
            questions[41] = "The use od Radix64 conversion:";
            answer1 = "Expands the message by 33%";
            answer2 = "Expands the message by 50%";
            answer3 = "Compresses the message" + "\n" + 
                      "by 33%";
            answer4 = "Compresses the message" + "\n" + 
                      "by 50%";
            Question q41 = new Question(questions[41], answer1, answer2, answer3, answer4, 4);
            questionList.add(q41);
            
            questions[42] = "Compressed message is:";
            answer1 = "Smaller than the original" + "\n" + 
                      "plaintext";
            answer2 = "Bigger than the original" + "\n" + 
                      "plaintext";
            answer3 = "Size depends on the message";
            answer4 = "Smaller only if confidentiality" + "\n" + 
                      "service is used";
            Question q42 = new Question(questions[42], answer1, answer2, answer3, answer4, 4);
            questionList.add(q42);
            
            questions[43] = "Compressed message is:";
            answer1 = "Smaller than the original" + "\n" + 
                      "plaintext";
            answer2 = "Bigger than the original" + "\n" + 
                      "plaintext";
            answer3 = "Size depends on the message";
            answer4 = "Smaller only if authentication" + "\n" + 
                      "service is used";
            Question q43 = new Question(questions[43], answer1, answer2, answer3, answer4, 4);
            questionList.add(q43);
            
            questions[44] = "Overal effect of compression and conversion ot Radix64 is:";
            answer1 = "Message is smaller than the" + "\n" + 
                      "original";
            answer2 = "Message is bigger than the" + "\n" + 
                      "original";
            answer3 = "Message is the same size as" + "\n" + 
                      "the original";
            answer4 = "Message is smaller only if" + "\n" + 
                      "confidentiality service is used";
            Question q44 = new Question(questions[44], answer1, answer2, answer3, answer4, 4);
            questionList.add(q44);
            
            questions[45] = "Overal effect of compression and conversion ot Radix64 is:";
            answer1 = "Message is smaller than the" + "\n" + 
                      "original";
            answer2 = "Message is bigger than the" + "\n" + 
                      "original";
            answer3 = "Message is the same size as" + "\n" + 
                      "the original";
            answer4 = "Message is smaller only if" + "\n" + 
                      "authentication service is used";
            Question q45 = new Question(questions[45], answer1, answer2, answer3, answer4, 4);
            questionList.add(q45);
            
            questions[46] = "Signing is always done:";
            answer1 = "Before compression";
            answer2 = "After compression";
            answer3 = "After encryption";
            answer4 = "After conversion to Radix64";
            Question q46 = new Question(questions[46], answer1, answer2, answer3, answer4, 5);
            questionList.add(q46);
            
            questions[47] = "Signing is always done:";
            answer1 = "Before encryption";
            answer2 = "After compression";
            answer3 = "After encryption";
            answer4 = "After conversion to Radix64";
            Question q47 = new Question(questions[47], answer1, answer2, answer3, answer4, 5);
            questionList.add(q47);
            
            questions[48] = "Signing is always done:";
            answer1 = "Before conversion to Radix64";
            answer2 = "After compression";
            answer3 = "After encryption";
            answer4 = "After conversion to Radix64";
            Question q48 = new Question(questions[48], answer1, answer2, answer3, answer4, 5);
            questionList.add(q48);
            
            questions[49] = "Compression is done:";
            answer1 = "After signing and before" + "\n" + 
                      "encryption";
            answer2 = "After encryption and before" + "\n" + 
                      "signing";
            answer3 = "After conversion to Radix64";
            answer4 = "Before signing";
            Question q49 = new Question(questions[49], answer1, answer2, answer3, answer4, 5);
            questionList.add(q49);
            
            questions[50] = "Encryption is done:";
            answer1 = "After signing";
            answer2 = "Before signing";
            answer3 = "Before compression";
            answer4 = "After conversion to Radix64";
            Question q50 = new Question(questions[50], answer1, answer2, answer3, answer4, 5);
            questionList.add(q50);
            
            questions[51] = "Encryption is done:";
            answer1 = "After compression";
            answer2 = "Before signing";
            answer3 = "Before compression";
            answer4 = "After conversion to Radix64";
            Question q51 = new Question(questions[51], answer1, answer2, answer3, answer4, 5);
            questionList.add(q51);
            
            questions[52] = "Encryption is done:";
            answer1 = "Before conversion to Radix64";
            answer2 = "Before signing";
            answer3 = "Before compression";
            answer4 = "After conversion to Radix64";
            Question q52 = new Question(questions[52], answer1, answer2, answer3, answer4, 5);
            questionList.add(q52);
            
            questions[53] = "Why is encryption applied after compression?";
            answer1 = "To strengthen security";
            answer2 = "To make message easier to read";
            answer3 = "To make message smaller";
            answer4 = "To make cryptanalysis easier";
            Question q53 = new Question(questions[53], answer1, answer2, answer3, answer4, 5);
            questionList.add(q53);
            
            questions[54] = "Conversion to Radix64 is done:";
            answer1 = "After signing";
            answer2 = "Before encryption";
            answer3 = "Before signing";
            answer4 = "Before compression";
            Question q54 = new Question(questions[54], answer1, answer2, answer3, answer4, 5);
            questionList.add(q54);
            
            questions[55] = "Conversion to Radix64 is done:";
            answer1 = "After encryption";
            answer2 = "Before encryption";
            answer3 = "Before signing";
            answer4 = "Before compression";
            Question q55 = new Question(questions[55], answer1, answer2, answer3, answer4, 5);
            questionList.add(q55);
            
            questions[56] = "Conversion to Radix64 is done:";
            answer1 = "After compression";
            answer2 = "Before encryption";
            answer3 = "Before signing";
            answer4 = "Before compression";
            Question q56 = new Question(questions[56], answer1, answer2, answer3, answer4, 5);
            questionList.add(q56);
            
            questions[57] = "Message decryption is done:";
            answer1 = "After conversion from Radix64";
            answer2 = "Before conversion from Radix64";
            answer3 = "After decompression";
            answer4 = "After signature viryfication";
            Question q57 = new Question(questions[57], answer1, answer2, answer3, answer4, 5);
            questionList.add(q57);
            
            questions[58] = "Message decryption is done:";
            answer1 = "Before decompression";
            answer2 = "Before conversion from Radix64";
            answer3 = "After decompression";
            answer4 = "After signature viryfication";
            Question q58 = new Question(questions[58], answer1, answer2, answer3, answer4, 5);
            questionList.add(q58);
            
            questions[59] = "Message decryption is done:";
            answer1 = "Before signature veryfication";
            answer2 = "Before conversion from Radix64";
            answer3 = "After decompression";
            answer4 = "After signature viryfication";
            Question q59 = new Question(questions[59], answer1, answer2, answer3, answer4, 5);
            questionList.add(q59);
            
            questions[60] = "Message authentication (signature veryfication) is done:";
            answer1 = "After message decryption";
            answer2 = "Before decompression";
            answer3 = "Before message decryption";
            answer4 = "Before conversion from Radix64";
            Question q60 = new Question(questions[60], answer1, answer2, answer3, answer4, 5);
            questionList.add(q60);
            
            questions[61] = "Message authentication (signature veryfication) is done:";
            answer1 = "After conversion from Radix64";
            answer2 = "Before decompression";
            answer3 = "Before message decryption";
            answer4 = "Before conversion from Radix64";
            Question q61 = new Question(questions[61], answer1, answer2, answer3, answer4, 5);
            questionList.add(q61);
            
            questions[62] = "Message authentication (signature veryfication) is done:";
            answer1 = "After decompression";
            answer2 = "Before decompression";
            answer3 = "Before message decryption";
            answer4 = "Before conversion from Radix64";
            Question q62 = new Question(questions[62], answer1, answer2, answer3, answer4, 5);
            questionList.add(q62);
            
            questions[63] = "Message longer than maximum length is:";
            answer1 = "Broken into smaller segments";
            answer2 = "Forbidden";
            answer3 = "Always broken up into 2" + "\n" +
                      "segments";
            answer4 = "Always broken up into 3" + "\n" +
                      "segments";
            Question q63 = new Question(questions[63], answer1, answer2, answer3, answer4, 6);
            questionList.add(q63);
            
            questions[64] = "When message is broken up into segments:";
            answer1 = "Each segment is mailed" + "\n" +
                      "separately";
            answer2 = "Segments are mailed two by two";
            answer3 = "Segments are mailed three by" + "\n" +
                      "three";
            answer4 = "Every segment has the same" + "\n" +
                      "header";
            Question q64 = new Question(questions[64], answer1, answer2, answer3, answer4, 6);
            questionList.add(q64);
            
            questions[65] = "Segmentation is done:";
            answer1 = "After all other services";
            answer2 = "Before all other services";
            answer3 = "Depends on the message";
            answer4 = "Depends on the sender";
            Question q65 = new Question(questions[65], answer1, answer2, answer3, answer4, 6);
            questionList.add(q65);
            
            questions[66] = "Maximum length of the message is 2000B and the message is" + "\n" +
                            "4050B long:";
            answer1 = "Message is broken into 3" + "\n" +
                      "segments";
            answer2 = "Message is broken into 4" + "\n" +
                      "segments";
            answer3 = "Message is broken into 2" + "\n" +
                      "segments";
            answer4 = "Message is broken into 6" + "\n" +
                      "segments";
            Question q66 = new Question(questions[66], answer1, answer2, answer3, answer4, 6);
            questionList.add(q66);
            
            questions[67] = "S2K specifiers are used to:";
            answer1 = "Convert passphrase to key";
            answer2 = "Convert key to passphrase";
            answer3 = "Store passphrase safely";
            answer4 = "Convert user ID to key";
            Question q67 = new Question(questions[67], answer1, answer2, answer3, answer4, 7);
            questionList.add(q67);
            
            questions[68] = "Simple S2K:";
            answer1 = "Hashes passphrase only";
            answer2 = "Hashes public key";
            answer3 = "Hashes passphrase and message";
            answer4 = "Hashes passphrase and salt" + "\n" +
                      "value";
            Question q68 = new Question(questions[68], answer1, answer2, answer3, answer4, 7);
            questionList.add(q68);
            
            questions[69] = "When using S2K to create session key and the hash code is" + "\n" + 
                            "longer than the key needed:";
            answer1 = "Leftmost octets are used";
            answer2 = "Rightmost octets are used";
            answer3 = "Middle octets are used";
            answer4 = "Depends on the algorithm" + "\n" +
                      "used";
            Question q69 = new Question(questions[69], answer1, answer2, answer3, answer4, 7);
            questionList.add(q69);
            
            questions[70] = "When using S2K to create session key and the hash code is" + "\n" + 
                            "shorter than the key needed:";
            answer1 = "Multiple instances of the hash" + "\n" +
                      "code are created";
            answer2 = "Part of the passphrase is" + "\n" +
                      "concatenated with hash code";
            answer3 = "Left half of the hash code" + "\n" +
                      "is duplicated";
            answer4 = "Right half of the hash code" + "\n" +
                      "is duplicated";
            Question q70 = new Question(questions[70], answer1, answer2, answer3, answer4, 7);
            questionList.add(q70);
            
            questions[71] = "Salted S2K:";
            answer1 = "Hashes passphrase and salt" + "\n" +
                      "value";
            answer2 = "Hashes salt value only";
            answer3 = "Hashes passphrase only";
            answer4 = "Hashes salt value and" + "\n" +
                      "passphrase separately";
            Question q71 = new Question(questions[71], answer1, answer2, answer3, answer4, 7);
            questionList.add(q71);
            
            questions[72] = "What is the salt value?";
            answer1 = "Random value specified in" + "\n" +
                      "S2K specifier";
            answer2 = "Hash code of the message";
            answer3 = "Hash code of the passphrase";
            answer4 = "Hash code of a random value";
            Question q72 = new Question(questions[72], answer1, answer2, answer3, answer4, 7);
            questionList.add(q72);
            
            questions[73] = "Public/Private key pairs are stored:";
            answer1 = "In private key ring of the" + "\n" +
                      "user";
            answer2 = "In public key ring of the" + "\n" +
                      "user";
            answer3 = "On the internet";
            answer4 = "In public key ring of other" + "\n" +
                      "users";
            Question q73 = new Question(questions[73], answer1, answer2, answer3, answer4, 8);
            questionList.add(q73);
            
            questions[74] = "Public keys of other users are stored:";
            answer1 = "In public key ring of the" + "\n" +
                      "user";
            answer2 = "In public key ring of the" + "\n" +
                      "public key owner";
            answer3 = "In private key ring of the" + "\n" +
                      "user";
            answer4 = "In private key ring of the" + "\n" +
                      "public key owner";
            Question q74 = new Question(questions[74], answer1, answer2, answer3, answer4, 8);
            questionList.add(q74);
            
            questions[75] = "User ID is typically what?";
            answer1 = "User's email address";
            answer2 = "Some random number";
            answer3 = "64 bits of public key";
            answer4 = "64 bits of private key";
            Question q75 = new Question(questions[75], answer1, answer2, answer3, answer4, 8);
            questionList.add(q75);
            
            questions[76] = "Key ID is what?";
            answer1 = "The least significant 64b" + "\n" +
                      "of the public key";
            answer2 = "The most significant 64b" + "\n" +
                      "of the public key";
            answer3 = "The most significant 64b" + "\n" +
                      "of the private key";
            answer4 = "The least significant 64b" + "\n" +
                      "of the private key";
            Question q76 = new Question(questions[76], answer1, answer2, answer3, answer4, 8);
            questionList.add(q76);
            
            questions[77] = "Private key in private key ring:";
            answer1 = "Is always encrypted";
            answer2 = "Is sometimes encrypted";
            answer3 = "Is always hashed";
            answer4 = "Is sometimes hashed";
            Question q77 = new Question(questions[77], answer1, answer2, answer3, answer4, 8);
            questionList.add(q77);
            
            questions[78] = "Private key in private key ring is encrypted:";
            answer1 = "Using hash code of the" + "\n" +
                      "password as the key";
            answer2 = "Using hash code of the" + "\n" +
                      "public key as the key";
            answer3 = "Using encrypted public key" + "\n" +
                      "as the key";
            answer4 = "Using encrypted password as" + "\n" +
                      "the key";
            Question q78 = new Question(questions[78], answer1, answer2, answer3, answer4, 8);
            questionList.add(q78);
            
            questions[79] = "Owner Trust is the field of:";
            answer1 = "Public key ring entry";
            answer2 = "Private key ring entry";
            answer3 = "Message";
            answer4 = "Signature";
            Question q79 = new Question(questions[79], answer1, answer2, answer3, answer4, 9);
            questionList.add(q79);
            
            questions[80] = "Key Legitimacy is the field of:";
            answer1 = "Public key ring entry";
            answer2 = "Private key ring entry";
            answer3 = "Message";
            answer4 = "Signature";
            Question q80 = new Question(questions[80], answer1, answer2, answer3, answer4, 9);
            questionList.add(q80);
            
            questions[81] = "Owner Trust field indicates:";
            answer1 = "The degree to which public key" + "\n" +
                      "owner is trusted to sign other" + "\n" + 
                      "public keys";
            answer2 = "The degree to which public key" + "\n" +
                      "owner is trusted to sign other" + "\n" + 
                      "private keys";
            answer3 = "The degree to which public key" + "\n" +
                      "owner is trusted to sign" + "\n" +
                      "messages";
            answer4 = "The degree to which private key" + "\n" +
                      "owner is trusted to sign other" + "\n" + 
                      "private keys";
            Question q81 = new Question(questions[81], answer1, answer2, answer3, answer4, 9);
            questionList.add(q81);
            
            questions[82] = "Signature Trust field in the public key ring:";
            answer1 = "Is the same as Owner Trust of" + "\n" +
                      "key that was used for signing";
            answer2 = "Is the different from Owner" + "\n" +
                      "Trust of the key that was" + "\n" + 
                      "used for signing";
            answer3 = "Is user defined";
            answer4 = "Is calculated";
            Question q82 = new Question(questions[82], answer1, answer2, answer3, answer4, 9);
            questionList.add(q82);
            
            questions[83] = "Key Legitimacy field is:";
            answer1 = "Calculated";
            answer2 = "User defined";
            answer3 = "Depends on the message";
            answer4 = "Depends on the key";
            Question q83 = new Question(questions[83], answer1, answer2, answer3, answer4, 9);
            questionList.add(q83);
            
            questions[84] = "How many signatures can be attached to public key in public" + "\n" +
                            "key ring?";
            answer1 = "More than one";
            answer2 = "Just one";
            answer3 = "Two or more";
            answer4 = "Less than three";
            Question q84 = new Question(questions[84], answer1, answer2, answer3, answer4, 10);
            questionList.add(q84);
            
            questions[85] = "How many signatures can be attached to public key in public" + "\n" +
                            "key ring?";
            answer1 = "Zero or more";
            answer2 = "Just one";
            answer3 = "Two or more";
            answer4 = "Less than three";
            Question q85 = new Question(questions[85], answer1, answer2, answer3, answer4, 10);
            questionList.add(q85);
            
            questions[86] = "The value of the Key Legitimacy field is calculated on the" + "\n" +
                            "basis of:";
            answer1 = "Signature Trust fields";
            answer2 = "Owner Trust field of that key";
            answer3 = "User IDs";
            answer4 = "Key IDs";
            Question q86 = new Question(questions[86], answer1, answer2, answer3, answer4, 10);
            questionList.add(q86);
            
            questions[87] = "If the owner of public key ring signes the key in that ring," + "\n" +
                            "Legitimacy of that key is";
            answer1 = "Automatically COMPLETE TRUST";
            answer2 = "Automatically MARGINAL TRUST";
            answer3 = "Depends on the key";
            answer4 = "UNKNOWN";
            Question q87 = new Question(questions[87], answer1, answer2, answer3, answer4, 10);
            questionList.add(q87);
            
            questions[88] = "When the user which public key Owner Trust field is set to " + "\n" +
                            "USUALLY TRUSTED signes some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINA TRUST";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            Question q88 = new Question(questions[88], answer1, answer2, answer3, answer4, 10);
            questionList.add(q88);
            
            questions[89] = "When the user which public key Owner Trust field is set to " + "\n" +
                            "NOT TRUSTED signes some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINAL TRUST";
            Question q89 = new Question(questions[89], answer1, answer2, answer3, answer4, 10);
            questionList.add(q89);
            
            questions[90] = "When the user which public key Owner Trust field is set to " + "\n" +
                            "ALWAYS TRUSTED signes some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINAL TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            Question q90 = new Question(questions[90], answer1, answer2, answer3, answer4, 10);
            questionList.add(q90);
            
            questions[91] = "When two user which public keys Owner Trust fields are set to " + "\n" +
                            "NOT TRUSTED sign some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINAL TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            Question q91 = new Question(questions[91], answer1, answer2, answer3, answer4, 10);
            questionList.add(q91);
            
            questions[92] = "When two user which public keys Owner Trust fields are set to " + "\n" +
                            "USUALLY TRUSTED sign some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINAL TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            Question q92 = new Question(questions[92], answer1, answer2, answer3, answer4, 10);
            questionList.add(q92);
            
            questions[93] = "When two user which public keys Owner Trust fields are set to " + "\n" +
                            "USUALLY TRUSTED and NOT TRUSTED sign some other key:";
            answer1 = "Legitimacy field for signed" + "\n" +
                      "key becomes MARGINAL TRUST";
            answer2 = "Legitimacy field for signed" + "\n" +
                      "key becomes UNKNOWN";
            answer3 = "Legitimacy field for signed" + "\n" +
                      "key becomes COMPLETE TRUST";
            answer4 = "Legitimacy field for signed" + "\n" +
                      "key becomes NOT TRUSTED";
            Question q93 = new Question(questions[93], answer1, answer2, answer3, answer4, 10);
            questionList.add(q93);
            
    }
    
}
