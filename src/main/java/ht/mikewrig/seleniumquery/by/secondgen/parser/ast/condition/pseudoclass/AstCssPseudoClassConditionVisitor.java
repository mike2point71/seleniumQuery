/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass;

import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssAnimatedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEvenPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssFirstPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssGtPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssHeaderPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLangPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLastPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLtPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNthPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssOddPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssRootPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssTargetPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssContainsPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssEmptyPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssHasPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssParentPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssButtonPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckboxPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssDisabledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssEnabledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFilePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFocusPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssImagePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssInputPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssPasswordPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssRadioPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssResetPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSelectedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSubmitPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssTextPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssFocusablePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssTabbablePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssBlankPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssFilledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssPresentPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssUncheckedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssHiddenPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssVisiblePseudoClass;

public interface AstCssPseudoClassConditionVisitor<T> {

    T visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass);
    T visit(AstCssEqPseudoClass astCssEqPseudoClass);
    T visit(AstCssEvenPseudoClass astCssEvenPseudoClass);
    T visit(AstCssFirstPseudoClass astCssFirstPseudoClass);
    T visit(AstCssGtPseudoClass astCssGtPseudoClass);
    T visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass);
    T visit(AstCssLangPseudoClass astCssLangPseudoClass);
    T visit(AstCssLastPseudoClass astCssLastPseudoClass);
    T visit(AstCssLtPseudoClass astCssLtPseudoClass);
    T visit(AstCssNotPseudoClass astCssNotPseudoClass);
    T visit(AstCssNthPseudoClass astCssNthPseudoClass);
    T visit(AstCssOddPseudoClass astCssOddPseudoClass);
    T visit(AstCssRootPseudoClass astCssRootPseudoClass);
    T visit(AstCssTargetPseudoClass astCssTargetPseudoClass);

    T visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass);
    T visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass);
    T visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass);
    T visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass);
    T visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass);
    T visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass);
    T visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass);
    T visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass);
    T visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass);
    T visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass);

    T visit(AstCssContainsPseudoClass astCssContainsPseudoClass);
    T visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass);
    T visit(AstCssHasPseudoClass astCssHasPseudoClass);
    T visit(AstCssParentPseudoClass astCssParentPseudoClass);

    T visit(AstCssButtonPseudoClass astCssButtonPseudoClass);
    T visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass);
    T visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass);
    T visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass);
    T visit(AstCssInputPseudoClass astCssInputPseudoClass);
    T visit(AstCssFilePseudoClass astCssFilePseudoClass);
    T visit(AstCssResetPseudoClass astCssResetPseudoClass);
    T visit(AstCssFocusPseudoClass astCssFocusPseudoClass);
    T visit(AstCssRadioPseudoClass astCssRadioPseudoClass);
    T visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass);
    T visit(AstCssImagePseudoClass astCssImagePseudoClass);
    T visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass);
    T visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass);
    T visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass);
    T visit(AstCssTextPseudoClass astCssTextPseudoClass);

    T visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass);
    T visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass);

    T visit(AstCssBlankPseudoClass astCssBlankPseudoClass);
    T visit(AstCssFilledPseudoClass astCssFilledPseudoClass);
    T visit(AstCssPresentPseudoClass astCssPresentPseudoClass);
    T visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass);

    T visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass);
    T visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass);

}
