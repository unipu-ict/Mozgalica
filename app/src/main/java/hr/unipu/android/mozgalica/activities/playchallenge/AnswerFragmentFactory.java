package hr.unipu.android.mozgalica.activities.playchallenge;

import java.util.HashMap;

import hr.unipu.android.mozgalica.BuildConfig;
import hr.unipu.android.mozgalica.activities.playchallenge.multiplechoice.MultipleChoiceFragment;
import hr.unipu.android.mozgalica.activities.playchallenge.selfcheck.SelfTestFragment;
import hr.unipu.android.mozgalica.activities.playchallenge.text.TextFragment;
import hr.unipu.android.mozgalica.database.ChallengeType;

/**
 *
 * Abstracts and bundles the creation of Fragments for Challenge Types.
 */
public class AnswerFragmentFactory {
    private static HashMap<Integer, AnswerFragmentCreator> challengeTypeFactories;

    static {
        challengeTypeFactories = new HashMap<>();
        challengeTypeFactories.put(ChallengeType.MULTIPLE_CHOICE, new AnswerFragmentCreator() {
            @Override
            public AnswerFragment createFragment() {
                return new MultipleChoiceFragment();
            }
        });
        challengeTypeFactories.put(ChallengeType.TEXT, new AnswerFragmentCreator() {
            @Override
            public AnswerFragment createFragment() {
                return new TextFragment();
            }
        });
        challengeTypeFactories.put(ChallengeType.SELF_TEST, new AnswerFragmentCreator() {
            @Override
            public AnswerFragment createFragment() {
                return new SelfTestFragment();
            }
        });
    }

    /**
     * Creates a fragment for the specified challengeType.
     *
     * @param challengeType one of ChallengeType.*
     * @return newly created fragment.
     */
    public AnswerFragment createFragmentForType(int challengeType) {
        AnswerFragmentCreator creator = challengeTypeFactories.get(challengeType);
        if (BuildConfig.DEBUG && creator == null) {
            throw new RuntimeException("Invalid Challenge Type " + challengeType);
        }

        return creator.createFragment();
    }

    /**
     * Interface to create an AnswerFragment
     */
    private interface AnswerFragmentCreator {
        AnswerFragment createFragment();
    }
}
