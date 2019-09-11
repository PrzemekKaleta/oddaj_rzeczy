document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);


    }



    createElements() {


      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {

    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;


      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();

    }

    /**
     * All events that are happening in form
     */
    events() {


      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();

        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;


      // TODO: get data from inputs and show them in summary


        // TODO: take information about institution

        var step3 = document.getElementById("step-3");

        var allRadio = step3.getElementsByTagName('input');

        var allRadioNames = step3.getElementsByClassName('title');

        var chosenInstitution;

        for (var i = 0; i < allRadio.length ; i++){

            if(allRadio[i].checked){
                chosenInstitution = allRadioNames[i].innerText;
                break;
            }};

        var institution = document.getElementById("institution");

        institution.innerText = chosenInstitution.replace('Fundacja', 'Dla fundacji');


        // TODO: take information about address

        // TODO: take informaion about date and time of pickup


        // text information about bags and they content

        var bags = document.getElementById("bags");

        var part = document.getElementById("quantity");

        var step1 = document.getElementById("step-1");

        var chosenCategory = [];

        var allInput = step1.getElementsByTagName('input');

        var allCheckboxNames = step1.getElementsByClassName('description');

        for (var i = 0; i < allInput.length ; i++){

            if(allInput[i].checked){
                chosenCategory.push(allCheckboxNames[i].innerText);
            }}

        var textbegining;

          if(part.value === 1){
              textbegining = "1 worek zawierający ";}
          else if(part.value<5){
              textbegining = part.value + " worki zawierające ";}
          else{
              textbegining = part.value + " worków zawierających ";}

        var textend;

          console.log(chosenCategory.length);

          if(chosenCategory.length === 1){
            textend = chosenCategory[0]
          }else if(chosenCategory.length === 2){
            textend = chosenCategory.join(" oraz ");
          }else{
            var firsthalf = chosenCategory.slice(0,(chosenCategory.length-2));
            var secondhalf = chosenCategory.slice((chosenCategory.length-2),(chosenCategory.length));
            var textfirsthalf = firsthalf.join(", ");
            var textsecondhalf = secondhalf.join(" oraz ");
            textend = textfirsthalf.concat(", ",textsecondhalf);
          }

        bags.innerText = textbegining.concat(" ", textend);

    }


  }
  const form = document.querySelector(".form--steps");
    //new

  if (form !== null) {
    new FormSteps(form);
  }

});
