<template>
  <div id="app">
    <header-nav />
    <router-view />
    <svg class="cursor" width="20" height="20" viewBox="0 0 20 20">
      <circle class="cursor__inner" cx="10" cy="10" r="5" />
    </svg>
  </div>
</template>

<script>
import HeaderNav from '@/components/common/HeaderNav.vue';
import { gsap } from 'gsap';
import LocomotiveScroll from 'locomotive-scroll';
import imagesLoaded from 'imagesloaded';
import WebFont from 'webfontloader';

// Map number x from range [a, b] to [c, d]
const map = (x, a, b, c, d) => (x - a) * (d - c) / (b - a) + c;

// Linear interpolation
const lerp = (a, b, n) => (1 - n) * a + n * b;

const clamp = (num, min, max) => num <= min ? min : num >= max ? max : num;

const randomNumber = (min, max) => Math.floor(Math.random() * (max - min + 1) + min);

// Gets the mouse position
const getMousePos = e => {
  return {
    x: e.clientX,
    y: e.clientY
  };
};

// Preload images
const preloadImages = (selector = 'img') => {
  return new Promise((resolve) => {
    imagesLoaded(document.querySelectorAll(selector), { background: true }, resolve);
  });
};

// Preload fonts
const preloadFonts = (id) => {
  return new Promise((resolve) => {
    WebFont.load({
      typekit: {
        id: id
      },
      active: resolve
    });
  });
};

export default {
  components:{HeaderNav},
  mounted() {
    const lscroll = new LocomotiveScroll({
      el: this.$el.querySelector('[data-scroll-container]'),
      smooth: true,
      direction: 'horizontal'
    });

    const elems = [...this.$el.querySelectorAll('.gallery__item')];
    const rotationsArr = Array.from({ length: elems.length }, () => randomNumber(-30, 30));
    const translationArr = Array.from({ length: elems.length }, () => randomNumber(-100, 100));
    lscroll.on('scroll', (obj) => {
      for (const key of Object.keys(obj.currentElements)) {
        const el = obj.currentElements[key].el;
        const idx = elems.indexOf(el);
        if (obj.currentElements[key].el.classList.contains('gallery__item')) {
          let progress = obj.currentElements[key].progress;
          const rotationVal = progress > 0.6 ? clamp(map(progress, 0.6, 1, 0, rotationsArr[idx]), Math.min(0, rotationsArr[idx]), Math.max(0, rotationsArr[idx])) : 0;
          const translationVal = progress > 0.6 ? clamp(map(progress, 0.6, 1, 0, translationArr[idx]), Math.min(0, translationArr[idx]), Math.max(0, translationArr[idx])) : 0;
          obj.currentElements[key].el.style.transform = `translateY(${translationVal}%) rotate(${rotationVal}deg)`;
        }
      }
    });
    lscroll.update();

    Promise.all([preloadImages('.gallery__item-imginner'), preloadFonts('vxy2fer')]).then(() => {
      document.body.classList.remove('loading');
      const cursor = new Cursor(this.$el.querySelector('.cursor'));
      [...this.$el.querySelectorAll('a,.gallery__item-img,.gallery__item-number')].forEach(link => {
        link.addEventListener('mouseenter', () => cursor.enter());
        link.addEventListener('mouseleave', () => cursor.leave());
      });
    });
  }
};

let mouse = {x: 0, y: 0};
window.addEventListener('mousemove', ev => mouse = getMousePos(ev));

class Cursor {
  constructor(el) {
    this.DOM = { el: el };
    this.DOM.el.style.opacity = 0;
    this.bounds = this.DOM.el.getBoundingClientRect();
    this.renderedStyles = {
      tx: { previous: 0, current: 0, amt: 0.2 },
      ty: { previous: 0, current: 0, amt: 0.2 },
      scale: { previous: 1, current: 1, amt: 0.15 }
    };
    this.onMouseMoveEv = () => {
      this.renderedStyles.tx.previous = this.renderedStyles.tx.current = mouse.x - this.bounds.width / 2;
      this.renderedStyles.ty.previous = this.renderedStyles.ty.previous = mouse.y - this.bounds.height / 2;
      gsap.to(this.DOM.el, { duration: 0.9, ease: 'Power3.easeOut', opacity: 1 });
      requestAnimationFrame(() => this.render());
      window.removeEventListener('mousemove', this.onMouseMoveEv);
    };
    window.addEventListener('mousemove', this.onMouseMoveEv);
  }
  enter() {
    this.renderedStyles['scale'].current = 2.5;
  }
  leave() {
    this.renderedStyles['scale'].current = 1;
  }
  render() {
    this.renderedStyles['tx'].current = mouse.x - this.bounds.width / 2;
    this.renderedStyles['ty'].current = mouse.y - this.bounds.height / 2;

    for (const key in this.renderedStyles) {
      this.renderedStyles[key].previous = lerp(this.renderedStyles[key].previous, this.renderedStyles[key].current, this.renderedStyles[key].amt);
    }

    this.DOM.el.style.transform = `translateX(${this.renderedStyles['tx'].previous}px) translateY(${this.renderedStyles['ty'].previous}px) scale(${this.renderedStyles['scale'].previous})`;

    requestAnimationFrame(() => this.render());
  }
}


</script>

<style></style>
