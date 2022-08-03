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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssAnimatedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEqPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEvenPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssFirstPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssGtPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssHeaderPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLangPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLastPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLtPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNotPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNthPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssOddPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssRootPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssTargetPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyChildPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyOfTypePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssContainsPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssEmptyPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssHasPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssParentPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssButtonPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckboxPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssDisabledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssEnabledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFilePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFocusPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssImagePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssPasswordPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssRadioPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssResetPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSelectedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSubmitPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssTextPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssFocusablePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssTabbablePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssBlankPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssFilledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssPresentPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssUncheckedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssHiddenPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssVisiblePseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassConditionVisitor;
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

public class CssPseudoClassConditionVisitor implements AstCssPseudoClassConditionVisitor<CssPseudoClassCondition> {

    @Override
    public CssPseudoClassCondition visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass) {
        return new CssAnimatedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEqPseudoClass astCssEqPseudoClass) {
        return new CssEqPseudoClass(astCssEqPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEvenPseudoClass astCssEvenPseudoClass) {
        return new CssEvenPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstPseudoClass astCssFirstPseudoClass) {
        return new CssFirstPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssGtPseudoClass astCssGtPseudoClass) {
        return new CssGtPseudoClass(astCssGtPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass) {
        return new CssHeaderPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLangPseudoClass astCssLangPseudoClass) {
        return new CssLangPseudoClass(astCssLangPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastPseudoClass astCssLastPseudoClass) {
        return new CssLastPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLtPseudoClass astCssLtPseudoClass) {
        return new CssLtPseudoClass(astCssLtPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNotPseudoClass astCssNotPseudoClass) {
        return new CssNotPseudoClass(astCssNotPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthPseudoClass astCssNthPseudoClass) {
        return new CssNthPseudoClass(astCssNthPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOddPseudoClass astCssOddPseudoClass) {
        return new CssOddPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssRootPseudoClass astCssRootPseudoClass) {
        return new CssRootPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTargetPseudoClass astCssTargetPseudoClass) {
        return new CssTargetPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass) {
        return new CssFirstChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass) {
        return new CssFirstOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass) {
        return new CssLastChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass) {
        return new CssLastOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass) {
        return new CssNthChildPseudoClass(astCssNthChildPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass) {
        return new CssNthLastChildPseudoClass(astCssNthLastChildPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass) {
        return new CssNthLastOfTypePseudoClass(astCssNthLastOfTypePseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass) {
        return new CssNthOfTypePseudoClass(astCssNthOfTypePseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass) {
        return new CssOnlyChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass) {
        return new CssOnlyOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssContainsPseudoClass astCssContainsPseudoClass) {
        return new CssContainsPseudoClass(astCssContainsPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass) {
        return new CssEmptyPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHasPseudoClass astCssHasPseudoClass) {
        return new CssHasPseudoClass(astCssHasPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssParentPseudoClass astCssParentPseudoClass) {
        return new CssParentPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssButtonPseudoClass astCssButtonPseudoClass) {
        return new CssButtonPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass) {
        return new CssCheckboxPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass) {
        return new CssCheckedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass) {
        return new CssDisabledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssInputPseudoClass astCssInputPseudoClass) {
        return new CssInputPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFilePseudoClass astCssFilePseudoClass) {
        return new CssFilePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssResetPseudoClass astCssResetPseudoClass) {
        return new CssResetPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFocusPseudoClass astCssFocusPseudoClass) {
        return new CssFocusPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssRadioPseudoClass astCssRadioPseudoClass) {
        return new CssRadioPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass) {
        return new CssSelectedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssImagePseudoClass astCssImagePseudoClass) {
        return new CssImagePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass) {
        return new CssEnabledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass) {
        return new CssPasswordPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass) {
        return new CssSubmitPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTextPseudoClass astCssTextPseudoClass) {
        return new CssTextPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass) {
        return new CssFocusablePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass) {
        return new CssTabbablePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssBlankPseudoClass astCssBlankPseudoClass) {
        return new CssBlankPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFilledPseudoClass astCssFilledPseudoClass) {
        return new CssFilledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssPresentPseudoClass astCssPresentPseudoClass) {
        return new CssPresentPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass) {
        return new CssUncheckedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass) {
        return new CssHiddenPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass) {
        return new CssVisiblePseudoClass();
    }

}
